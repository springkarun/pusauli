package com.digital_pusauli.view.login_upload.RegisterShop

import android.annotation.TargetApi
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.digital_pusauli.R
import com.digital_pusauli.app.AppController
import com.digital_pusauli.restservices.APIService
import com.digital_pusauli.restservices.ApiUtils
import com.digital_pusauli.utils.ConnectivityReceiver
import com.digital_pusauli.utils.CustomProgressBar
import com.digital_pusauli.utils.UtilityImageUpload
import com.digital_pusauli.utils.UtilityImageUpload.Companion.ALL_PERMISSIONS_RESULT
import com.digital_pusauli.utils.Utils
import com.digital_pusauli.utils.Utils.toast
import com.google.gson.Gson
import com.theartofdev.edmodo.cropper.CropImage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_upload_shop.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody


class UploadShopActivity : AppCompatActivity(),ConnectivityReceiver.ConnectivityReceiverListener {

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showSnack(isConnected)
    }

    val picIntance: UtilityImageUpload by lazy { UtilityImageUpload(this@UploadShopActivity) }
    var ischeck: Int = 0
    private val TAG = UploadShopActivity::class.java.simpleName
    private var apiServices: APIService? = null
    private lateinit var pb: CustomProgressBar
    private lateinit var context: Context


    private var bitmap_fornt: Bitmap? = null
    private var bitmap_back: Bitmap? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_shop)
        context = this@UploadShopActivity
        Utils.leftToRight(this@UploadShopActivity)
        apiServices = ApiUtils.apiService


        back_img_btn.setOnClickListener {
            startActivityForResult(picIntance.pickImageChooserIntent, 369)
            ischeck = 1

        }
        btn_logo.setOnClickListener {
            startActivityForResult(picIntance.pickImageChooserIntent, 370)
            ischeck = 2
        }

        btn_submit.setOnClickListener {

            if (ConnectivityReceiver.isConnected) {
                try {
                    initJson()
                } catch (e: Exception) {
                }

            } else {
                try {
                    // jsonParse()
                    showSnack(false);
                } catch (e: Exception) {
                }
            }
        }

/*

        // Back Arrow press
        back_arrow.setOnClickListener {
            onBackPressed()
            Utils.rightToLeft(this@UploadShopActivity)
        }
*/

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == AppCompatActivity.RESULT_OK) {
            if (requestCode == 369) {
                if (picIntance.getPickImageResultUri(data) != null) {
                    val picUri = picIntance.getPickImageResultUri(data)
                    picIntance.crop_Method(picUri!!)
                }
            } else if (requestCode == 370) {
                if (picIntance.getPickImageResultUri(data) != null) {
                    val picUri = picIntance.getPickImageResultUri(data)
                    picIntance.crop_Method(picUri!!)
                }
            }
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && ischeck == 1) {
            if (data != null) {
                bitmap_back = picIntance.CropParseImage(resultCode, data)
                imgBack.setImageBitmap(bitmap_back)
                ischeck = 0
            }
        } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && ischeck == 2) {
            if (data != null) {
                bitmap_fornt = picIntance.CropParseImage(resultCode, data)
                logo.setImageBitmap(bitmap_fornt)
                ischeck = 0
            }
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




    override fun onResume() {
        super.onResume()
        AppController.getInstance().setConnectivityListener(this)


    }

    override fun onBackPressed() {
        super.onBackPressed()
        Utils.rightToLeft(this@UploadShopActivity)
    }


    private fun initJson(){

        val bitmap_fornt = picIntance.onCaptureImageResult(bitmap_fornt!!)
        val bitmap_back = picIntance.onCaptureImageResult(bitmap_back!!)
        log("bitmap_fornt path-> $bitmap_fornt ,  bitmap_back path-> $bitmap_back" )
        pb = CustomProgressBar(this)
        pb.setCancelable(false)
        pb.show()


        val name="efe"
        val age="53"
        val mob="565468657"


        apiServices!!.uploadPhoto(
                picIntance.multi(name),
                picIntance.multi(age),
                picIntance.multi(mob),
                picIntance.multiImg(bitmap_fornt))

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            result -> log("Response JSON = ${Gson().toJson(result.data)}")
                            pb.dismiss()

                           // if(result.status) {
                                toast(result.message)
                        },
                        { error ->
                            pb.dismiss()
                            log("ERROR ${error.message }")
                        }
                )
    }







    private fun log(mess:String){
        Utils.log(TAG,mess)
    }

    private fun showSnack(isConnected: Boolean) {
        if (isConnected)
            Utils.showSnackBar(this, getString(R.string.network_status), Color.GREEN)
        else
            Utils.showSnackBar(this, getString(R.string.network_status_false), Color.RED)
    }


}


