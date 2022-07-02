package com.example.keepthetime_apipractice_20220618

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.keepthetime_apipractice_20220618.adapters.SearchedUserAdapter
import com.example.keepthetime_apipractice_20220618.databinding.ActivityAddFriendBinding
import com.example.keepthetime_apipractice_20220618.datas.BasicResponse
import com.example.keepthetime_apipractice_20220618.datas.UserData
import com.example.keepthetime_apipractice_20220618.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddFriendActivity : BaseActivity() {
    lateinit var binding: ActivityAddFriendBinding
    lateinit var mAdapter: SearchedUserAdapter
    val mSearchedUserList = ArrayList<UserData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_friend)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        binding.btnSearch.setOnClickListener {
            val keyword = binding.edtNickname.text.toString()

            apiList.getRequestSearchUser(ContextUtil.getLoginUserToken(mContext), keyword)
                .enqueue(object: Callback<BasicResponse> {
                    override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                        if (response.isSuccessful) {
                            val br = response.body()!!
                            mSearchedUserList.clear()
                            mSearchedUserList.addAll(br.data.users)
                            mAdapter.notifyDataSetChanged()
                        }
                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                    }

                })
        }
    }

    override fun setValues() {
        mAdapter = SearchedUserAdapter(mContext, R.layout.searched_user_list_item, mSearchedUserList)
        binding.searchedUserListView.adapter = mAdapter
    }
}