package com.example.keepthetime_apipractice_20220618.api

import android.provider.ContactsContract
import com.example.keepthetime_apipractice_20220618.datas.BasicResponse
import retrofit2.Call
import retrofit2.http.*

interface APIList {

    @FormUrlEncoded
    @POST("/user")
    fun postRequestLogin(
        @Field("email") email: String,
        @Field("password") pw: String,
    ): Call<BasicResponse>

    @FormUrlEncoded
    @PUT("/user")
    fun putRequestSignUp(
        @Field("email") email: String,
        @Field("password") pw: String,
        @Field("nick_name") nickname: String,
    ): Call<BasicResponse>

    @GET("/user/check")
    fun getRequestUserCheck(
        @Query("type") type: String,
        @Query("value") value: String,
    ): Call<BasicResponse>

    @GET("/user")
    fun getRequestUserInfo(
        @Header("X-Http-Token") token: String,
    ): Call<BasicResponse>

    @FormUrlEncoded
    @PATCH("/user/password")
    fun patchRequestEditPassword(
        @Header("X-Http-Token") token: String,
        @Field("current_password") curPw: String,
        @Field("new_password") newPw: String,
    ): Call<BasicResponse>

    @GET("/search/user")
    fun getRequestSearchUser(
        @Header("X-Http-Token") token: String,
        @Query("nickname") nickname: String,
    ): Call<BasicResponse>
}