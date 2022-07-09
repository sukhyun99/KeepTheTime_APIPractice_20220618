package com.example.keepthetime_apipractice_20220618

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.keepthetime_apipractice_20220618.utils.ContextUtil

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setValues()
        setupEvents()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
        val myHandler = Handler(Looper.getMainLooper())

        myHandler.postDelayed({
            if (ContextUtil.isAutoLogin(mContext) && ContextUtil.getLoginUserToken(mContext) != "") {
                val myIntent = Intent(mContext, MainActivity::class.java)
                startActivity(myIntent)
                finish()
            } else {
                val myIntent = Intent(mContext, LoginActivity::class.java)
                startActivity(myIntent)
                finish()
            }
        }, 2500)
    }
}