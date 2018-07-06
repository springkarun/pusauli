package com.digital_pusauli.view.categroy_menu

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.MenuItem
import com.digital_pusauli.R
import com.digital_pusauli.app.AppController
import com.digital_pusauli.utils.ConnectivityReceiver
import com.digital_pusauli.utils.Utils
import com.digital_pusauli.utils.Utils.toast
import com.digital_pusauli.view.SplashActivity
import com.digital_pusauli.view.list_menu.Settings
import com.digital_pusauli.view.login_upload.login.LoginUploadActivity
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.app_bar_navigation.*
import kotlinx.android.synthetic.main.content_navigation.*
import technolifestyle.com.imageslider.FlipperLayout
import technolifestyle.com.imageslider.FlipperView
import java.util.*


class NavigationActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener , ConnectivityReceiver.ConnectivityReceiverListener {
    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showSnack(isConnected)
    }

    var prefs: SharedPreferences? = null

    private val TAG = NavigationActivity::class.java.simpleName
    private lateinit var flipperLayout: FlipperLayout
    private lateinit var list:ArrayList<CategoriModel>
    private lateinit var context: Context



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        setSupportActionBar(toolbar)
        context=this@NavigationActivity

        prefs = this.getSharedPreferences("Language_pref", 0)



        list= ArrayList()
        list.add(CategoriModel(R.drawable.ic_medicine, getString(R.string.medical),"Medical"))
        list.add(CategoriModel(R.drawable.ic_doctor, getString(R.string.hospital),"Hospital"))
        list.add(CategoriModel(R.drawable.ic_light_bulb, getString(R.string.electronic),"Electronic"))
        list.add(CategoriModel(R.drawable.ic_grocery, getString(R.string.grocery),"General_Store"))
        list.add(CategoriModel(R.drawable.ic_smartphone, getString(R.string.mobile),"Sweets"))
        list.add(CategoriModel(R.drawable.ic_bank_store, getString(R.string.bank),"Bank"))
        list.add(CategoriModel(R.drawable.ic_necklace, getString(R.string.jewels),"Medical"))
        list.add(CategoriModel(R.drawable.ic_laddu, getString(R.string.sweets),"Medical"))
        list.add(CategoriModel(R.drawable.ic_apple, getString(R.string.fruite),"Medical"))
        list.add(CategoriModel(R.drawable.vegetable, getString(R.string.vegetable),"Medical"))
        list.add(CategoriModel(R.drawable.ic_shoes, getString(R.string.shoes),"Medical"))
        list.add(CategoriModel(R.drawable.ic_cloth, getString(R.string.cloth),"Medical"))
        list.add(CategoriModel(R.drawable.ic_carpenter, getString(R.string.carpenter),"Medical"))
        list.add(CategoriModel(R.drawable.ic_farincher, getString(R.string.farincher),"Medical"))
        list.add(CategoriModel(R.drawable.ic_restaurants, getString(R.string.restaurants),"Medical"))
        list.add(CategoriModel(R.drawable.ic_school, getString(R.string.school),"Medical"))
        list.add(CategoriModel(R.drawable.ic_stacenary, getString(R.string.stacenary),"Medical"))
        list.add(CategoriModel(R.drawable.ic_hotel, getString(R.string.hotel),"Medical"))
        list.add(CategoriModel(R.drawable.ic_temple, getString(R.string.temple),"Medical"))
        list.add(CategoriModel(R.drawable.ic_vehicle, getString(R.string.vehicle),"Medical"))
        list.add(CategoriModel(R.drawable.ic_petrol_pump, getString(R.string.petrol),"Medical"))



        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = CategoryAdapter(list)


        flipperLayout = findViewById(R.id.flipper_layout)
        setLayout()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onResume() {
        super.onResume()
        AppController.getInstance().setConnectivityListener(this)
    }




    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_login -> {
                startActivity(Intent(this@NavigationActivity, LoginUploadActivity::class.java))

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_settings -> {
                startActivity(Intent(this@NavigationActivity, Settings::class.java))
            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }






    private fun setLayout() {
        val url = arrayOf("https://images1.phoenixnewtimes.com/imager/u/745xauto/10176715/asiana_-_bakery_storefront_-_escarcega.png",
                "https://studentcareercoach.files.wordpress.com/2012/06/bigstock_portrait_of_happy_teens_lookin_13855142.jpg",
                "http://tastegrowersmarket.com.au/wp-content/uploads/2016/03/produce.jpg",
                "http://cdn.odishatv.in/wp-content/uploads/2017/11/school-student.jpg",
                "https://randomwire.com/wp-content/uploads/hua-cheng-bei-electronics.jpeg",
                "http://images.newindianexpress.com/uploads/user/imagelibrary/2017/4/18/original/Where4.jpg")

        for (i in 0..5) {
            val view = FlipperView(baseContext)
            view.imageUrl = url[i]
            // .setDescription("Cool" + (i + 1));
            flipperLayout.addFlipperView(view)
            view.setOnFlipperClickListener {
                toast("Here " + (flipperLayout.currentPagePosition + 1))
            }
        }
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

    private var doubleBackToExitPressedOnce = false

    override fun onBackPressed() {

        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        }

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        toast(getString(R.string.back_press))
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }

}
