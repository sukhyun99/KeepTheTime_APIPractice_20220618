package com.example.keepthetime_apipractice_20220618

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.keepthetime_apipractice_20220618.adapters.SearchedUserAdapter
import com.example.keepthetime_apipractice_20220618.adapters.SearchedUserRecyclerAdapter
import com.example.keepthetime_apipractice_20220618.databinding.ActivityAddFriendRecyclerViewBinding
import com.example.keepthetime_apipractice_20220618.datas.BasicResponse
import com.example.keepthetime_apipractice_20220618.datas.UserData
import com.example.keepthetime_apipractice_20220618.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddFriendRecyclerViewActivity : BaseActivity() {
    lateinit var binding: ActivityAddFriendRecyclerViewBinding
    lateinit var mAdapter: SearchedUserRecyclerAdapter
    var mSearchedUserList = ArrayList<UserData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_friend_recycler_view)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        binding.btnSearch.setOnClickListener {
            val inputKeyword = binding.edtNickname.text.toString()
            apiList.getRequestSearchUser(ContextUtil.getLoginUserToken(mContext), inputKeyword)
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
        mAdapter = SearchedUserRecyclerAdapter(mContext, mSearchedUserList)
        binding.searchedUserRecyclerView.adapter = mAdapter
        binding.searchedUserRecyclerView.layoutManager = GridLayoutManager(mContext, 2)
//        binding.searchedUserRecyclerView.layoutManager = LinearLayoutManager(mContext)
    }
}