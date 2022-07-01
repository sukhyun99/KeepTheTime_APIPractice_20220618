package com.example.keepthetime_apipractice_20220618

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.keepthetime_apipractice_20220618.databinding.ActivitySignUpBinding
import com.example.keepthetime_apipractice_20220618.datas.BasicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : BaseActivity() {
    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        binding.btnSignUp.setOnClickListener {
            val inputEmail = binding.edtEmail.text.toString()
            val inputPassword = binding.edtPassword.text.toString()
            val inputNickname = binding.edtNickname.text.toString()

        apiList.putRequestSignUp(inputEmail, inputPassword, inputNickname).enqueue(object : Callback<BasicResponse> {
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(mContext, "회원가입에 성공했습니다.", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(mContext, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })
        }

        binding.btnCheckEmail.setOnClickListener {
            val inputEmail = binding.edtEmail.text.toString()

            apiList.getRequestUserCheck("EMAIL", inputEmail).enqueue(object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if (response.isSuccessful) {
                        binding.txtEmailCheckResult.text = "사용해도 좋은 이메일입니다."
                    }
                    else {
                        binding.txtEmailCheckResult.text = "이미 사용중인 이메일입니다."
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            })
        }
    }

    override fun setValues() {

    }
}