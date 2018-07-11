package com.digital_pusauli.view.login_upload.RegisterShop

import android.annotation.TargetApi
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.digital_pusauli.R
import com.digital_pusauli.utils.UtilityImageUpload
import com.digital_pusauli.utils.UtilityImageUpload.Companion.ALL_PERMISSIONS_RESULT
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.android.synthetic.main.activity_upload_shop.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class UploadShopActivity : AppCompatActivity() {

    val picIntance: UtilityImageUpload by lazy { UtilityImageUpload(this@UploadShopActivity) }
    var ischeck:Int=0


    private val TAG = UploadShopActivity::class.java.simpleName
    private val CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034
    private val SERVER_PATH = "http://api.canvatechnoloy.in"
    private val access_token = "10448859925a8d6d090c67f"
    private var uploadAPIService: APIService? = null
    var progressDialog: ProgressDialog? = null

    private var bitmap_fornt: Bitmap? = null
    private var bitmap_back: Bitmap? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_shop)
        init()


        back_img_btn.setOnClickListener {
            startActivityForResult(picIntance.pickImageChooserIntent, 369)
            ischeck=1

        }
        btn_logo.setOnClickListener {
            startActivityForResult(picIntance.pickImageChooserIntent, 370)
            ischeck=2
        }

        btn_submit.setOnClickListener { initJsonOperation() }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == AppCompatActivity.RESULT_OK) {
            if (requestCode == 369) {
                if (picIntance.getPickImageResultUri(data) != null) {
                    val picUri = picIntance.getPickImageResultUri(data)
                    picIntance.crop_Method(picUri!!)
                }
            }else if (requestCode == 370) {
                if (picIntance.getPickImageResultUri(data) != null) {
                    val picUri = picIntance.getPickImageResultUri(data)
                    picIntance.crop_Method(picUri!!)
                }
            }
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && ischeck==1 ) {
            bitmap_back=picIntance.CropParseImage(resultCode, data)
            imgBack.setImageBitmap(bitmap_back)
            ischeck=0
        } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE  && ischeck==2) {
            bitmap_fornt=picIntance.CropParseImage(resultCode, data)
            logo.setImageBitmap(bitmap_fornt)
            ischeck=0
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            ALL_PERMISSIONS_RESULT -> {
                for (perms in picIntance.permissionsToRequest!!) {
                    if (picIntance.hasPermission(perms)) {

                    } else {

                        picIntance.permissionsRejected.add(perms)
                    }
                }

                if (picIntance.permissionsRejected.size > 0) {


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(picIntance.permissionsRejected[0])) {
                            picIntance.showMessageOKCancel("These permissions are mandatory for the application. Please allow access.",
                                    DialogInterface.OnClickListener { dialog, which ->
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                                            //Log.d("API123", "permisionrejected " + permissionsRejected.size());
                                            requestPermissions(picIntance.permissionsRejected.toTypedArray(), ALL_PERMISSIONS_RESULT)
                                        }
                                    })
                            return
                        }
                    }

                }
            }
        }

    }






    private fun init() {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        uploadAPIService = Retrofit.Builder()
                .baseUrl(SERVER_PATH)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIService::class.java)

        progressDialog = ProgressDialog(this)
        progressDialog!!.setMessage("Uploading...")
    }



    private fun initJsonOperation() {

        progressDialog!!.show()
        val file = picIntance.onCaptureImageResult(bitmap_back!!)
        Log.d(TAG, "filePath=$file")


        val mFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

      //  val mFile = RequestBody.create(MediaType.parse("image/*"), file)
        val fileToUpload = MultipartBody.Part.createFormData("file", file.name, mFile)


        val name = RequestBody.create(MediaType.parse("text/plain"), edit_shopName.text.toString())
        val age = RequestBody.create(MediaType.parse("text/plain"),  edit_shopReg.text.toString())
        val mobile = RequestBody.create(MediaType.parse("text/plain"),  edit_shopMobile.text.toString())

        val fileUpload = uploadAPIService!!.uploadSingleFile(fileToUpload, name, age, mobile)
        fileUpload.enqueue(object : Callback<Model> {
            override fun onResponse(call: Call<Model>, response: Response<Model>) {
                progressDialog!!.dismiss()

                Log.d(TAG, "Response " + "Success " + response.body()!!.message)
                Toast.makeText(this@UploadShopActivity, "Response " +response.body()!!.message, Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<Model>, t: Throwable) {
                progressDialog!!.dismiss()
                Log.d(TAG, "Error " + t.message)
            }

        })
    }






}


