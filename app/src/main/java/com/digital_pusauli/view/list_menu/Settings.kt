package com.digital_pusauli.view.list_menu

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.digital_pusauli.R
import com.digital_pusauli.utils.CustomProgressBar
import com.digital_pusauli.view.SplashActivity
import kotlinx.android.synthetic.main.activitty_settings.*


class Settings : AppCompatActivity() {

    var prefs: SharedPreferences? = null
    private lateinit var pb: CustomProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activitty_settings)
        prefs = this.getSharedPreferences("Language_pref", 0)


        btn_english.setOnClickListener {

            val editor = prefs!!.edit()
            editor.putString("key_lang", "en")
            editor.apply()
            pb = CustomProgressBar(this)
            pb.setCancelable(false)
            pb.show()


            Handler().postDelayed({
                pb.dismiss()
                startActivity(Intent(this@Settings,SplashActivity::class.java))
                finish()},2000)


        }
        btn_hindi.setOnClickListener {

            val editor = prefs!!.edit()
            editor.putString("key_lang", "hi")
            editor.apply()

            pb = CustomProgressBar(this)
            pb.setCancelable(false)
            pb.show()


            Handler().postDelayed({
                pb.dismiss()
                startActivity(Intent(this@Settings,SplashActivity::class.java))
                finish()},2000)


        }



    }



}
