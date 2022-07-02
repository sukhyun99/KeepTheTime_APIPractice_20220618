package com.example.keepthetime_apipractice_20220618

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.keepthetime_apipractice_20220618.databinding.ActivityEditPasswordBinding
import com.example.keepthetime_apipractice_20220618.datas.BasicResponse
import com.example.keepthetime_apipractice_20220618.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditPasswordActivity : BaseActivity() {
    lateinit var binding: ActivityEditPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_password)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        binding.btnEdtPassword.setOnClickListener {
            apiList.patchRequestEditPassword(ContextUtil.getLoginUserToken(mContext)
                , binding.edtCurrentPassword.text.toString(), binding.edtNewPassword.text.toString())
                .enqueue(object: Callback<BasicResponse> {
                    override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse> ) {
                        if (response.isSuccessful) {
                            val br = response.body()!!

                            Toast.makeText(mContext, "비밀번호가 변경되었습니다.", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            Toast.makeText(mContext, "비밀번호가 변경실패.", Toast.LENGTH_SHORT).show()
                            Log.d("결과", response.code().toString())
                        }
                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                        Toast.makeText(mContext, "통신 실패.", Toast.LENGTH_SHORT).show()
                    }

                })
        }


    }

    override fun setValues() {

    }
}