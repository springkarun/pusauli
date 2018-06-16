package com.digital_pusauli.view.categroy

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import com.digital_pusauli.R
import com.digital_pusauli.app.AppController
import com.digital_pusauli.model.Result
import com.digital_pusauli.restservices.APIService
import com.digital_pusauli.restservices.ApiUtils
import com.digital_pusauli.utils.ConnectivityReceiver
import com.digital_pusauli.utils.CustomProgressBar
import com.digital_pusauli.utils.Utils
import com.digital_pusauli.view.sub_option.Sub_Classs_info
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_categroies.*
import java.util.*


class CategoryActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showSnack(isConnected)
    }


    private val TAG = CategoryActivity::class.java.simpleName
    private var apiServices: APIService? = null
    private lateinit var context: Context
    private lateinit var list: ArrayList<Result>
    private lateinit var recyclerView:RecyclerView
    private lateinit var mAdapter:CategoryAdapters
    private lateinit var pb: CustomProgressBar

    private var catg_name=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context=this@CategoryActivity

        Utils.leftToRight(this@CategoryActivity)
        setContentView(R.layout.activity_categroies)

        val extras = intent.extras
        if (extras != null) {
            try {
                catg_name=extras.getString("key")
            } catch (e: Exception) {
            }
        }

        apiServices=ApiUtils.apiService

        initViews()


        if (ConnectivityReceiver.isConnected) {
            try {
                initJson(catg_name)
            } catch (e: Exception) {
            }

        } else {
            try {
                // jsonParse()
                showSnack(false);
            } catch (e: Exception) {
            }
        }


        // Back Arrow press
        back_arrow.setOnClickListener {
               onBackPressed()
               Utils.rightToLeft(this@CategoryActivity)
        }

        txt_title.text=catg_name



        val ss= SpannableString(resources.getString(R.string.results_found))
     //    val bold= StyleSpan(Typeface.BOLD)
        val bold_italic= StyleSpan(Typeface.BOLD_ITALIC)
      //  val italic= StyleSpan(Typeface.ITALIC)
      //  val underline= UnderlineSpan()
      //  val striket= StrikethroughSpan()
        ss.setSpan(bold_italic,0,16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        //ss.setSpan(bold,0,2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        //ss.setSpan(underline,8,12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
       // ss.setSpan(striket,14,16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_item_not.text=ss.let{it}




    }

    override fun onResume() {
        super.onResume()
        AppController.getInstance().setConnectivityListener(this)


    }

    override fun onBackPressed() {
        super.onBackPressed()
        Utils.rightToLeft(this@CategoryActivity)
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recy_categroies)
        recyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager



    recyclerView.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            internal var gestureDetector = GestureDetector(applicationContext, object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    return true
                }
            })

            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                val child = rv.findChildViewUnder(e.x, e.y)
                if (child != null && gestureDetector.onTouchEvent(e)) {
                    val position = rv.getChildAdapterPosition(child)
                    val ii = list[position]
                    startActivity(Intent(context,Sub_Classs_info::class.java).putExtra("key",ii))
                }
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
            }
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
            }
        })



    }


    private fun initJson(catg_name:String){

        pb = CustomProgressBar(this)
        pb.setCancelable(false)
        pb.show()



        apiServices!!.getCategrory(catg_name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            result -> log("Response JSON = ${Gson().toJson(result.result)}")
                            pb.dismiss()

                            if(result.status) {
                                tv_item_not.visibility=View.GONE
                                list = ArrayList(result.result)
                                mAdapter = CategoryAdapters(list)
                                recyclerView.adapter = mAdapter
                            }else{
                                tv_item_not.visibility=View.VISIBLE
                            }


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
    private fun toast(mess:String){
        Utils.toast(context,mess)
    }
    private fun showSnack(isConnected: Boolean) {
        if (isConnected)
            Utils.showToast(this, getString(R.string.network_status), Color.GREEN)
        else
            Utils.showToast(this, getString(R.string.network_status_false), Color.RED)
    }




}