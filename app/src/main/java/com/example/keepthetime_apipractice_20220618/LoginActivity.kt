package com.example.keepthetime_apipractice_20220618

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.keepthetime_apipractice_20220618.databinding.ActivityLoginBinding
import com.example.keepthetime_apipractice_20220618.datas.BasicResponse
import com.example.keepthetime_apipractice_20220618.utils.ContextUtil
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
        binding.btnSignUp.setOnClickListener {
            val myIntent = Intent(mContext, SignUpActivity::class.java)
            startActivity(myIntent)
        }

        binding.btnLogin.setOnClickListener {
            val inputEmail = binding.edtEmail.text.toString()
            val inputPw = binding.edtPassword.text.toString()

            apiList.postRequestLogin(inputEmail, inputPw).enqueue(object: Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if (response.isSuccessful) {
                        val br = response.body()!!

                        ContextUtil.setLoginUserToken(mContext, br.data.token)

                        Toast.makeText(mContext, "${br.data.user.nickname} 님 환영합니다.", Toast.LENGTH_SHORT).show()

                        val myIntent = Intent(mContext, MainActivity::class.java)
                        startActivity(myIntent)

                        finish()
                    }
                    else {
                        
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                    
                }


            })
        }

        binding.autoLoginCheckBox.setOnCheckedChangeListener { CompoundButton, isChecked ->
            ContextUtil.setAutoLogin(mContext, isChecked)
        }
    }

    override fun setValues() {
        binding.autoLoginCheckBox.isChecked = ContextUtil.isAutoLogin(mContext)
    }
}