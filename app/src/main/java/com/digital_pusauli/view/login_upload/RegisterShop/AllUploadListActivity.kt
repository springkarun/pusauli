package com.digital_pusauli.view.login_upload.RegisterShop

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.digital_pusauli.R
import com.digital_pusauli.adapter.AllUploadListAdapter
import com.digital_pusauli.app.AppController
import com.digital_pusauli.model.Data
import com.digital_pusauli.restservices.APIService
import com.digital_pusauli.restservices.ApiUtils
import com.digital_pusauli.utils.ConnectivityReceiver
import com.digital_pusauli.utils.CustomProgressBar
import com.digital_pusauli.utils.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_all_upload_list_layout.*


class AllUploadListActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showSnack(isConnected)
    }


    private lateinit var list: ArrayList<Data>
    private lateinit var adp: AllUploadListAdapter
    private lateinit var context: AppCompatActivity
    private var apiServices: APIService? = null
    private val TAG = AllUploadListActivity::class.java.simpleName
    private lateinit var pb: CustomProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this@AllUploadListActivity
        Utils.leftToRight(this@AllUploadListActivity)
        setContentView(R.layout.activity_all_upload_list_layout)


        apiServices = ApiUtils.apiService
        list = ArrayList()

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


        fab.setOnClickListener {
            startActivity(Intent(context, UploadShopActivity::class.java))
        }

        recy_list.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
    }


    override fun onResume() {
        super.onResume()
        AppController.getInstance().setConnectivityListener(this)
    }


    private fun loadCategoryShopAll() {
        pb = CustomProgressBar(this)
        pb.setCancelable(false)
        pb.show()


        apiServices!!.getShowShopAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            pb.dismiss()
                            if (result.status!!) {
                                for (i in result.data!!) {
                                    list.add(i!!)
                                }
                                adp = AllUploadListAdapter(list)
                                recy_list.adapter = adp
                            }
                        },
                        { error ->
                            pb.dismiss()
                            log("ERROR ${error.message}")
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