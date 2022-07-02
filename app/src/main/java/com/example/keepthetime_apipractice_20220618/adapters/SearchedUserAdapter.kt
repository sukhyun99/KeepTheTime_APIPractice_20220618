package com.example.keepthetime_apipractice_20220618.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.keepthetime_apipractice_20220618.R
import com.example.keepthetime_apipractice_20220618.datas.UserData
import java.util.zip.Inflater

class SearchedUserAdapter(
    val mContext: Context,
    val resId: Int,
    val mList: ArrayList<UserData>,
): ArrayAdapter<UserData>(mContext, resId, mList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView

        if (tempRow == null) {
            tempRow = LayoutInflater.from(mContext).inflate(resId, null)
        }

        val row = tempRow!!
        val data = mList[position]

        val imgProfile = row.findViewById<ImageView>(R.id.imgProfile)
        val txtNickname = row.findViewById<TextView>(R.id.txtNickname)

        txtNickname.text = data.nickname
        Glide.with(mContext).load(data.profileImageURL).into(imgProfile)

        return row
    }
}