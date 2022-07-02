package com.example.keepthetime_apipractice_20220618.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.keepthetime_apipractice_20220618.api.APIList
import com.example.keepthetime_apipractice_20220618.api.ServerAPI

abstract class BaseFragment: Fragment() {
    lateinit var mContext: Context
    lateinit var apiList: APIList

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mContext = requireContext()

        val myRetrofit = ServerAPI.getRetrofit()
        apiList = myRetrofit.create(APIList::class.java)
    }

    abstract fun setupEvents()
    abstract fun setValues()
}