package com.digital_pusauli

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.app_bar_navigation.*
import kotlinx.android.synthetic.main.content_navigation.*
import technolifestyle.com.imageslider.FlipperLayout
import technolifestyle.com.imageslider.FlipperView

class NavigationActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var flipperLayout: FlipperLayout
    private lateinit var list:ArrayList<CategoriModel>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        setSupportActionBar(toolbar)

        list= ArrayList()
        list.add(CategoriModel(R.drawable.ic_medicine,getString(R.string.medical)))
        list.add(CategoriModel(R.drawable.ic_doctor,getString(R.string.hospital)))
        list.add(CategoriModel(R.drawable.ic_light_bulb,getString(R.string.electronic)))
        list.add(CategoriModel(R.drawable.ic_grocery,getString(R.string.grocery)))
        list.add(CategoriModel(R.drawable.ic_smartphone,getString(R.string.mobile)))
        list.add(CategoriModel(R.drawable.ic_bank_store,getString(R.string.bank)))
        list.add(CategoriModel(R.drawable.ic_necklace,getString(R.string.jewels)))
        list.add(CategoriModel(R.drawable.ic_laddu,getString(R.string.sweets)))
        list.add(CategoriModel(R.drawable.ic_apple,getString(R.string.fruite)))
        list.add(CategoriModel(R.drawable.vegetable,getString(R.string.vegetable)))
        list.add(CategoriModel(R.drawable.ic_shoes,getString(R.string.shoes)))
        list.add(CategoriModel(R.drawable.ic_cloth,getString(R.string.cloth)))
        list.add(CategoriModel(R.drawable.ic_carpenter,getString(R.string.carpenter)))
        list.add(CategoriModel(R.drawable.ic_farincher,getString(R.string.farincher)))
        list.add(CategoriModel(R.drawable.ic_restaurants,getString(R.string.restaurants)))
        list.add(CategoriModel(R.drawable.ic_school,getString(R.string.school)))
        list.add(CategoriModel(R.drawable.ic_stacenary,getString(R.string.stacenary)))
        list.add(CategoriModel(R.drawable.ic_hotel,getString(R.string.hotel)))
        list.add(CategoriModel(R.drawable.ic_temple,getString(R.string.temple)))
        list.add(CategoriModel(R.drawable.ic_vehicle,getString(R.string.vehicle)))
        list.add(CategoriModel(R.drawable.ic_petrol_pump,getString(R.string.petrol)))




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

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

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
        val url = arrayOf("http://images.indianexpress.com/2017/12/national-anthem-lead.jpg",
                "https://studentcareercoach.files.wordpress.com/2012/06/bigstock_portrait_of_happy_teens_lookin_13855142.jpg",
                "https://static.socialpost.news/wp-content/uploads/2018/02/students.jpg",
                "http://cdn.odishatv.in/wp-content/uploads/2017/11/school-student.jpg",
                "http://www.esamaad.com/images/Power-Grid.jpg",
                "http://images.newindianexpress.com/uploads/user/imagelibrary/2017/4/18/original/Where4.jpg")

        for (i in 0..5) {
            val view = FlipperView(baseContext)
            view.imageUrl = url[i]
            // .setDescription("Cool" + (i + 1));
            flipperLayout.addFlipperView(view)
            view.setOnFlipperClickListener { Toast.makeText(this@NavigationActivity,
                    "Here " + (flipperLayout.currentPagePosition + 1),
                    Toast.LENGTH_SHORT).show() }
        }
    }
}
