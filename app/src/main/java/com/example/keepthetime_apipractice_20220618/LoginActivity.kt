package com.example.keepthetime_apipractice_20220618

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.keepthetime_apipractice_20220618.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : BaseActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        binding.btnLogin.setOnClickListener {
            val inputEmail = binding.edtEmail.text.toString()
            val inputPw = binding.edtPassword.text.toString()

            apiList.postRequestLogin(inputEmail, inputPw).enqueue(object: Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful) {
                        val bodyStr = response.body()!!
                        Log.d("성공 응답 본문", bodyStr)
                    }
                    else {
                        val bodyStr = response.errorBody()!!.string()
                        Log.d("실패 응답 본문", bodyStr)
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d("실패 응답", "실패")
                }

            })
        }
    }

    override fun setValues() {

    }
}