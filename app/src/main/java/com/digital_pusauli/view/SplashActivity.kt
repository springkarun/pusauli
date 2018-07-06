package com.digital_pusauli.view

import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.digital_pusauli.R
import com.digital_pusauli.view.categroy_menu.NavigationActivity
import java.util.*

class SplashActivity : AppCompatActivity() {

    var prefs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        prefs = this.getSharedPreferences("Language_pref", 0)
        val languageToLoad = prefs!!.getString("key_lang", "en")

        //  val languageToLoad = "hi"
        val locale = Locale(languageToLoad)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)



      //  Handler().postDelayed({

           startActivity(Intent(this@SplashActivity, NavigationActivity::class.java))
            finish()

      //  }, 2000)
    }


}