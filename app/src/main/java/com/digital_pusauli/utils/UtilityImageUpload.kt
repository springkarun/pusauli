package com.digital_pusauli.utils

import android.Manifest
import android.content.ComponentName
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.Parcelable
import android.provider.MediaStore
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.theartofdev.edmodo.cropper.CropImage
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


class UtilityImageUpload(activity: AppCompatActivity) {

    var permissionsToRequest: ArrayList<String>? = null
    val permissionsRejected = ArrayList<String>()
    val permissions = ArrayList<String>()

    var activity:AppCompatActivity?=null

    init {
        this.activity=activity

        permissions.add(Manifest.permission.CAMERA)
        permissionsToRequest = findUnAskedPermissions(permissions)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (permissionsToRequest!!.size > 0)
                activity.requestPermissions(permissionsToRequest!!.toTypedArray(), ALL_PERMISSIONS_RESULT)
        }
    }


    //permisstion

    fun findUnAskedPermissions(wanted: ArrayList<String>): ArrayList<String> {
        val result = ArrayList<String>()

        for (perm in wanted) {
            if (!hasPermission(perm)) {
                result.add(perm)
            }
        }

        return result
    }

    fun hasPermission(permission: String): Boolean {
        if (canMakeSmores()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return activity!!.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
            }
        }
        return true
    }

    fun showMessageOKCancel(message: String, okListener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(activity!!)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show()
    }

    fun canMakeSmores(): Boolean {
        return Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1
    }


    companion object {

         val ALL_PERMISSIONS_RESULT = 107

         fun rotateImageIfRequired(img: Bitmap, selectedImage: Uri): Bitmap {

            val ei = ExifInterface(selectedImage.path)
            val orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)

            return when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> rotateImage(img, 90)
                ExifInterface.ORIENTATION_ROTATE_180 -> rotateImage(img, 180)
                ExifInterface.ORIENTATION_ROTATE_270 -> rotateImage(img, 270)
                else -> img
            }
        }

        fun rotateImage(img: Bitmap, degree: Int): Bitmap {
            val matrix = Matrix()
            matrix.postRotate(degree.toFloat())
            val rotatedImg = Bitmap.createBitmap(img, 0, 0, img.width, img.height, matrix, true)
            img.recycle()
            return rotatedImg
        }
    }



    fun crop_Method(imageUri: Uri) {
        CropImage.activity(imageUri).start(activity!!)
    }


    fun CropParseImage(resultCode:Int,data:Intent?):Bitmap{
        var bm:Bitmap?=null
        val result = CropImage.getActivityResult(data!!)
        if (resultCode == AppCompatActivity.RESULT_OK) {

            val crop_resultUri = result.uri

            val bitmap = MediaStore.Images.Media.getBitmap(activity!!.contentResolver, crop_resultUri)
            if (bitmap != null) {
                bm = getResizedBitmap(rotateImageIfRequired(bitmap, crop_resultUri), 500)
                //imgBack.setImageBitmap(bm)
            }

        } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
            Log.d("Upload....", "EditProfile Crop Image error: ${result.error}")
        }
        return bm!!
    }






     fun getResizedBitmap(image: Bitmap, maxSize: Int): Bitmap {
        var width = image.width
        var height = image.height

        val bitmapRatio = width.toFloat() / height.toFloat()
        if (bitmapRatio > 0) {
            width = maxSize
            height = (width / bitmapRatio).toInt()
        } else {
            height = maxSize
            width = (height * bitmapRatio).toInt()
        }
        return Bitmap.createScaledBitmap(image, width, height, true)
    }


     fun getPickImageResultUri(data: Intent?): Uri? {
        var isCamera = true
        if (data != null) {
            val action = data.action
            isCamera = action != null && action == MediaStore.ACTION_IMAGE_CAPTURE
        }
        return if (isCamera) captureImageOutputUri else data!!.data
    }

     val captureImageOutputUri: Uri?
        get() {
            var outputFileUri: Uri? = null
            val getImage = activity!!.externalCacheDir
            if (getImage != null) {
                outputFileUri = Uri.fromFile(File(getImage.path, "profile.png"))
            }
            return outputFileUri
        }


     val pickImageChooserIntent: Intent get() {
        val outputFileUri = captureImageOutputUri
        val allIntents = ArrayList<Intent>()
        val packageManager = activity!!.packageManager
        val captureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val listCam = packageManager.queryIntentActivities(captureIntent, 0)
        for (res in listCam) {
            val intent = Intent(captureIntent)
            intent.component = ComponentName(res.activityInfo.packageName, res.activityInfo.name)
            intent.`package` = res.activityInfo.packageName
            if (outputFileUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri)
            }
            allIntents.add(intent)
        }
        val galleryIntent = Intent(Intent.ACTION_GET_CONTENT)
        galleryIntent.type = "image/*"
        val listGallery = packageManager.queryIntentActivities(galleryIntent, 0)
        for (res in listGallery) {
            val intent = Intent(galleryIntent)
            intent.component = ComponentName(res.activityInfo.packageName, res.activityInfo.name)
            intent.`package` = res.activityInfo.packageName
            allIntents.add(intent)
        }
        var mainIntent = allIntents[allIntents.size - 1]
        for (intent in allIntents) {
            if (intent.component!!.className == "com.android.documentsui.DocumentsActivity") {
                mainIntent = intent
                break
            }
        }
        allIntents.remove(mainIntent)
        val chooserIntent = Intent.createChooser(mainIntent, "Select source")
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, allIntents.toTypedArray<Parcelable>())

        return chooserIntent
    }



   //file upload
   fun onCaptureImageResult(bitmap: Bitmap): File {
        val imgFile: File = File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis().toString() + ".jpg")
       val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
       val fo: FileOutputStream
        try {
            imgFile.createNewFile()
            fo = FileOutputStream(imgFile)
            fo.write(bytes.toByteArray())
            fo.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return imgFile
    }



      //Multpart Data text
     fun multi(s:String): RequestBody {
        return RequestBody.create(MediaType.parse("text/plain"),s)
     }

    //Multpart Data file
    fun multiImg(s:File,key:String): MultipartBody.Part {
        //  val mFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        val mFile = RequestBody.create(MediaType.parse("image/*"), s)
        val fileToUpload = MultipartBody.Part.createFormData(key, s.name, mFile)
        return fileToUpload
    }


}


