package com.digital_pusauli.view.login_upload.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.digital_pusauli.R
import com.digital_pusauli.utils.Alerters
import com.digital_pusauli.view.login_upload.RegisterShop.UploadShopActivity
import kotlinx.android.synthetic.main.activity_login_upload.*

class LoginUploadActivity : AppCompatActivity() {


    private lateinit var context: LoginUploadActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context=this@LoginUploadActivity

        setContentView(R.layout.activity_login_upload)

        btn_login.setOnClickListener {
            val id=ids.text.toString()
            val password=pass.text.toString()


            if(id.isBlank()) {
                Alerters(context, "Please enter UserID")
            }else if(password.isBlank()) {
                Alerters(context, "Please enter Password")
            }else if(id=="kk" && password=="pune"){
                startActivity(Intent(context, UploadShopActivity::class.java))

                ids.text.clear()
                pass.text.clear()
            }else Alerters(context,"Inviald Username or Password")


        }
    }
}
