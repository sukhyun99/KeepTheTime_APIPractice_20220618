package com.example.keepthetime_apipractice_20220618.datas

import com.google.gson.annotations.SerializedName

data class UserData(
    val id: Int,
    val provider: String,
    val uid: String?,
    val email: String,
    @SerializedName("ready_minute")
    val readyMinute: Int,
    @SerializedName("nick_name")
    val nickname: String,
    @SerializedName("profile_img")
    val profileImageURL: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
)
