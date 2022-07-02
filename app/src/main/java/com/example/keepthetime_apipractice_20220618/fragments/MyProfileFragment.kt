package com.example.keepthetime_apipractice_20220618.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.keepthetime_apipractice_20220618.EditPasswordActivity
import com.example.keepthetime_apipractice_20220618.R
import com.example.keepthetime_apipractice_20220618.api.APIList
import com.example.keepthetime_apipractice_20220618.databinding.FragmentMyProfileBinding
import com.example.keepthetime_apipractice_20220618.datas.BasicResponse
import com.example.keepthetime_apipractice_20220618.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyProfileFragment: BaseFragment() {
    lateinit var binding: FragmentMyProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_profile, container, false)
        return  binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        apiList.getRequestUserInfo(ContextUtil.getLoginUserToken(mContext))
            .enqueue(object: Callback<BasicResponse> {
                override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                    if (response.isSuccessful) {
                        val br = response.body()!!

                        binding.txtNickname.text = br.data.user.nickname
                        binding.txtEmail.text = br.data.user.email

                        Glide.with(mContext).load(br.data.user.profileImageURL).into(binding.imgProfile)
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            })

        binding.btnEdtPassword.setOnClickListener {
            val myIntent = Intent(mContext, EditPasswordActivity::class.java)
            startActivity(myIntent)
        }
    }

    override fun setValues() {

    }
}