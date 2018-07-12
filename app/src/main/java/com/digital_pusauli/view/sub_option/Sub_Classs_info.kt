package com.digital_pusauli.view.sub_option

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.WindowManager
import com.digital_pusauli.R
import com.digital_pusauli.app.Constant
import com.digital_pusauli.model.Data
import com.digital_pusauli.utils.Utils
import com.digital_pusauli.utils.loadFromUrl
import com.digital_pusauli.view.sub_option.menu_option.about.About_Fragment
import com.digital_pusauli.view.sub_option.menu_option.couress.Courses_Fragment
import com.digital_pusauli.view.sub_option.menu_option.gallery.Gallery_Fragment
import com.digital_pusauli.view.sub_option.menu_option.infrastructure_frament.Infrastructure_Fragment
import kotlinx.android.synthetic.main.sub_fragment_main.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*


class Sub_Classs_info : AppCompatActivity() {



    private lateinit var result: Data

    private var bg_color: String? = null
    private var bundle: Bundle? = null
    private var loadJson1: String? = null
    private var loadJson2: String? = null
    private var loadJson3: String? = null
    private val loadJson4: String? = null
    private var loadJson5: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.leftToRight(this@Sub_Classs_info)
        setContentView(R.layout.sub_fragment_main)


        val extras = intent.extras
        if (extras != null) {
            result=extras.getParcelable("key")
        }



        val mToolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(mToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        mToolbar.setNavigationOnClickListener({onBackPressed()
            Utils.rightToLeft(this@Sub_Classs_info)
        })


        bundle = Bundle()
        bg_color = "#0097A7"
       // bg_color = "#${result.colorCode}"








        //setToolbar()
        jsonParse()


        collapse_toolbar.title = result.shopName
        collapse_toolbar.setContentScrimColor(Color.parseColor(bg_color))
        collapse_toolbar.setCollapsedTitleTextAppearance(R.style.collapsedappbar)
        collapse_toolbar.setExpandedTitleTextAppearance(R.style.expandedappbar)
        toolbar.title = result.ownerName
        collapse_toolbar.isTitleEnabled = false
        app_bar_layout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {

            internal var isVisible = true
            internal var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    toolbar.title = result.shopName
                    isVisible = true
                } else if (isVisible) {
                    toolbar.title = ""
                    isVisible = false
                }
            }
        })


        tabs.setupWithViewPager(viewpager)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.parseColor(bg_color)
        }
        tabs.setBackgroundColor(Color.parseColor(bg_color))
        tabs.tabGravity = TabLayout.GRAVITY_FILL


        cl_name.text = result.shopName
        location_city.text = result.shopAddress
        image_paralax.loadFromUrl(Constant.BASE_URL_Image +result.shopAvatar)
        clg_logo.loadFromUrl(Constant.BASE_URL_Image +result.ownerContact)




    }
    override fun onBackPressed() {
        super.onBackPressed()
        Utils.rightToLeft(this@Sub_Classs_info)
    }

    internal inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFrag(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }
    }
    private fun setupViewPager(viewPager: ViewPager) {

        tabs.tabGravity = TabLayout.GRAVITY_FILL;
        tabs.tabMode = TabLayout.MODE_SCROLLABLE;
        val adapter = ViewPagerAdapter(supportFragmentManager)


        bundle!!.putSerializable("ABOUT", loadJson2)
        val about = About_Fragment.newInstance()
        about.arguments = bundle
        adapter.addFrag(about, "ABOUT")


        bundle = Bundle()
        bundle!!.putSerializable("COURSES", loadJson1)
        val course = Courses_Fragment.newInstance()
        course.arguments = bundle
        adapter.addFrag(course, "COURSES")


        bundle!!.putSerializable("INFRASTRUCTURE", loadJson3)
        val infa = Infrastructure_Fragment.newInstance()
        infa.arguments = bundle
        adapter.addFrag(infa, "INFRASTRUCTURE")

        /*

        bundle.putSerializable("FACULTY", loadJson4);
        Fragment facu = Faculty_Fragment.newInstance();
        facu.setArguments(bundle);
        adapter.addFrag(facu,"FACULTY");
*/


        bundle!!.putSerializable("GALLERY", loadJson5)
        val gallery = Gallery_Fragment.newInstance()
        gallery.arguments = bundle
        adapter.addFrag(gallery, "GALLERY")


        /*

        bundle.putString("REVIEWS","REVIEWS");
        Fragment revie = Reviews_Fragment.newInstance();
        revie.setArguments(bundle);
        adapter.addFrag(revie,"REVIEWS");*/


        viewPager.adapter = adapter
    }


    private fun jsonParse() {


        try {

            val jo = JSONObject()
            jo.put("firstName", "John")
            jo.put("lastName", "Doe")

            val ja = JSONArray()
            ja.put(jo)

            loadJson1 = ja.toString()

            loadJson2 = ja.toString()

            loadJson3 = ja.toString()

            loadJson5 = ja.toString()


        } catch (e: JSONException) {
            e.printStackTrace()
        }

        setupViewPager(viewpager)

    }


    private fun setToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {onBackPressed()}
    }


}