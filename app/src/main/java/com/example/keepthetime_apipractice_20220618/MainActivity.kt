package com.example.keepthetime_apipractice_20220618

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.keepthetime_apipractice_20220618.adapters.MainViewPager2Adapter
import com.example.keepthetime_apipractice_20220618.databinding.ActivityMainBinding
import com.example.keepthetime_apipractice_20220618.datas.BasicResponse
import com.example.keepthetime_apipractice_20220618.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mvp2a: MainViewPager2Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        binding.mainBottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.schedule -> {
                    binding.mainViewPager2.currentItem = 0
                }

                R.id.myInfo -> {
                    binding.mainViewPager2.currentItem = 1
                }
            }

            return@setOnItemSelectedListener true
        }

        binding.mainViewPager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                binding.mainBottomNav.selectedItemId = when(position) {
                    0 -> R.id.schedule
                    else -> R.id.myInfo
                }
            }
        })
    }

    override fun setValues() {
        mvp2a = MainViewPager2Adapter(this)
        binding.mainViewPager2.adapter = mvp2a
    }
}