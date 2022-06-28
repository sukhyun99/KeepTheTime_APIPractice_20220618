package com.example.keepthetime_apipractice_20220618.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerAPI {

    companion object {
        private var retrofit : Retrofit? = null
        private val BASE_URL = "https://keepthetime.xyz"

        fun getRetrofit(): Retrofit {
            if (this.retrofit == null) {
                this.retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return this.retrofit!!
        }

    }
}