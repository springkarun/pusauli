package com.digital_pusauli.view.login_upload.RegisterShop

import android.annotation.TargetApi
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import com.digital_pusauli.R
import com.digital_pusauli.adapter.SpinnerAdapter
import com.digital_pusauli.app.AppController
import com.digital_pusauli.model.Data
import com.digital_pusauli.restservices.APIService
import com.digital_pusauli.restservices.ApiUtils
import com.digital_pusauli.utils.*
import com.digital_pusauli.utils.UtilityImageUpload.Companion.ALL_PERMISSIONS_RESULT
import com.digital_pusauli.utils.Utils.toast
import com.google.gson.Gson
import com.theartofdev.edmodo.cropper.CropImage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_upload_shop.*
import cn.pedant.SweetAlert.SweetAlertDialog




class UploadShopActivity : AppCompatActivity(),
        ConnectivityReceiver.ConnectivityReceiverListener,
        AdapterView.OnItemSelectedListener {

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showSnack(isConnected)
    }

    val picIntance: UtilityImageUpload by lazy { UtilityImageUpload(this@UploadShopActivity) }
    var ischeck: Int = 0
    private val TAG = UploadShopActivity::class.java.simpleName
    private var apiServices: APIService? = null
    private lateinit var pb: CustomProgressBar
    private lateinit var context: AppCompatActivity

    private lateinit var listSpinner: ArrayList<Data>
    private lateinit var adpSpinner: SpinnerAdapter
    private var SelectedSpinner:String?=null


    private var bitmap_fornt: Bitmap? = null
    private var bitmap_back: Bitmap? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_shop)
        context = this@UploadShopActivity
        Utils.leftToRight(this@UploadShopActivity)
        apiServices = ApiUtils.apiService

        listSpinner = ArrayList()


        if (ConnectivityReceiver.isConnected) {
            try {
                loadCategoryShopAll()
            } catch (e: Exception) {
            }

        } else {
            try {
                showSnack(false);
            } catch (e: Exception) {
            }
        }



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
                    isCheckValidation()
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


    private fun loadCategoryShopAll(){
        pb = CustomProgressBar(this)
        pb.setCancelable(false)
        pb.show()


        apiServices!!.getCategoryShopAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                             pb.dismiss()
                             if(result.status) {

                                 val d=Data()
                                 d.categoryId="0"
                                 d.categoryName="Select Category "
                                 listSpinner.add(0,d)

                                 for(i in result.data) {
                                     listSpinner.add(i)
                                 }
                                 adpSpinner = SpinnerAdapter(applicationContext, listSpinner)
                                 sp_category.adapter = adpSpinner
                                 sp_category.onItemSelectedListener = this
                             }
                        },
                        { error ->
                            pb.dismiss()
                            log("ERROR ${error.message }")
                        }
                )
         }

    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
        SelectedSpinner=listSpinner[position].categoryId!!
        toast( "SelectedSpinner : "+listSpinner[position].categoryId)

    }
    override fun onNothingSelected(arg0: AdapterView<*>) {}




    fun isCheckValidation(){


        val shopName=edit_shopName.text.toString()
        val shopReg=edit_shopReg.text.toString()
        val shopEmail=edit_shopEmail.text.toString()
        val shopMobile=edit_shopMobile.text.toString()
        val shopAddress=edit_shopAddress.text.toString()
        val shopNearst=edit_shopNearst.text.toString()
        val shop_time=edit_shopTime.text.toString()
        val ownerName=edit_ownerName.text.toString()
        val ownerEmail=edit_ownerEmail.text.toString()
        val owner_contact=edit_ownerContact.text.toString()

        val shopRating="324.5"
        val shopLatitude="34.6343"
        val shopLongitude="52.3423"
        val colorCode="#634543"



        if(SelectedSpinner == "0"){
            Alerters(context, "Please Select Category")
        }else if(shopName.isBlank()) {
            Alerters(context, "Please enter Shop Name")
        }else if(shopReg.isBlank()) {
            Alerters(context, "Please enter shopReg")
        } else if(shopEmail.isBlank()) {
            Alerters(context, "Please enter shopEmail")
        }else if(!shopEmail.isEmail()) {
            Alerters(context, "Please enter valid shopEmail")
        }else if(shopMobile.isBlank()) {
            Alerters(context, "Please enter shopMobile")
        }else if(shopMobile.length !=10 ) {
            Alerters(context, "Please enter valid shopMobile")
        } else if(shopAddress.isBlank()) {
            Alerters(context, "Please enter shopAddress")
        } else if(shopNearst.isBlank()) {
            Alerters(context, "Please enter shopNearst")
        }else if(shop_time.isBlank()) {
            Alerters(context, "Please enter shop_time")
        }else if(ownerName.isBlank()) {
            Alerters(context, "Please enter ownerName")
        }else if(ownerEmail.isBlank()) {
            Alerters(context, "Please enter ownerEmail")
        } else if(!ownerEmail.isEmail()) {
            Alerters(context, "Please enter valid ownerEmail")
        }else if(owner_contact.isBlank()) {
            Alerters(context, "Please enter owner contact")
        } else if(owner_contact.length !=10 ) {
            Alerters(context, "Please enter valid owner contact")
        }else if(bitmap_fornt == null ) {
            Alerters(context, "Please select fornt image")
        }else if(bitmap_back == null ) {
            Alerters(context, "Please select back image")
        }else {
            initJson(SelectedSpinner!!,shopName, shopReg, shopEmail, shopMobile, shopAddress,
                    shopNearst, shop_time, shopRating, shopLatitude,
                    shopLongitude, ownerName, ownerEmail,
                    owner_contact, colorCode, bitmap_fornt!!, bitmap_back!!)
        }


    }

    private fun clearField(){
        edit_shopName.text.clear()
        edit_shopReg.text.clear()
        edit_shopEmail.text.clear()
        edit_shopMobile.text.clear()
        edit_shopAddress.text.clear()
        edit_shopNearst.text.clear()
        edit_shopTime.text.clear()
        edit_ownerName.text.clear()
        edit_ownerEmail.text.clear()
        edit_ownerContact.text.clear()
    }

    private fun initJson(
            categoryId: String,
            shopName: String,
            shopReg: String,
            shopEmail: String,
            shopMobile: String,
            shopAddress: String,
            shopNearst: String,
            shop_time: String,
            shopRating: String,
            shopLatitude: String,
            shopLongitude: String,
            ownerName: String,
            ownerEmail: String,
            owner_contact: String,
            colorCode: String,
            bitmap_fornt_:Bitmap,
            bitmap_back_:Bitmap)
    {

        val _bitmap_fornt = picIntance.onCaptureImageResult(bitmap_fornt_)
        val _bitmap_back = picIntance.onCaptureImageResult(bitmap_back_)

        log("bitmap_fornt path-> $bitmap_fornt ,  bitmap_back path-> $bitmap_back" )

    //    dialogProgress(context).show()

        val pDialog = SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)
        pDialog.progressHelper.barColor = Color.parseColor("#A5DC86")
        pDialog.titleText = "Uploading..."
        pDialog.setCancelable(false)
        pDialog.show()

        apiServices!!.saveRegisterShop(
                picIntance.multi(categoryId),
                picIntance.multi(shopName),
                picIntance.multi(shopReg),
                picIntance.multi(shopEmail),
                picIntance.multi(shopMobile),
                picIntance.multi(shopAddress),
                picIntance.multi(shopNearst),
                picIntance.multi(shop_time),
                picIntance.multi(shopRating),
                picIntance.multi(shopLatitude),
                picIntance.multi(shopLongitude),
                picIntance.multi(ownerName),
                picIntance.multi(ownerEmail),
                picIntance.multi(owner_contact),
                picIntance.multi(colorCode),
                picIntance.multiImg(_bitmap_fornt,"ownerAvatar"),
                picIntance.multiImg(_bitmap_back,"shopAvatar"))

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            log("Response JSON = ${Gson().toJson(result.data)}")
                            pDialog.dismiss()
                            if(result.status) {
                            toast(result.message)
                                var shopId=""
                                for(i in result.data) {
                                    shopId= i.shopId!!
                                }
                                SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                                        .setTitleText("Your Shop Id : $shopId")
                                        .setContentText("Ok")
                                        .show()
                                clearField()
                            }
                        },
                        { error ->
                            pDialog.dismiss()
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


