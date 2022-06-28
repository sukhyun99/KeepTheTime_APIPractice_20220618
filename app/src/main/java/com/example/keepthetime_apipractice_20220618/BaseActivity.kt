package com.example.keepthetime_apipractice_20220618

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.keepthetime_apipractice_20220618.api.APIList
import com.example.keepthetime_apipractice_20220618.api.ServerAPI

abstract class BaseActivity: AppCompatActivity() {
    lateinit var mContext: Context
    lateinit var apiList: APIList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this

        val myRetrofit = ServerAPI.getRetrofit()
        apiList = myRetrofit.create(APIList::class.java)
    }
    abstract fun setupEvents()
    abstract fun setValues()
}