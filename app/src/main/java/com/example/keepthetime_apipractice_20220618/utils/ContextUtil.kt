package com.example.keepthetime_apipractice_20220618.utils

import android.content.Context

class ContextUtil {

    companion object {
        private val prefName = "KeepTheTimePref"
        private val LOGIN_USER_TOKEN = "LOGIN_USER_TOKEN"
        private val AUTO_LOGIN = "AUTO_LOGIN"

        fun setLoginUserToken(context: Context, token: String) {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            pref.edit().putString(LOGIN_USER_TOKEN, token).apply()
        }

        fun getLoginUserToken(context: Context): String {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getString(LOGIN_USER_TOKEN, "")!!
        }

        fun setAutoLogin(context: Context, isAutoLogin: Boolean) {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            pref.edit().putBoolean(AUTO_LOGIN, isAutoLogin).apply()
        }

        fun isAutoLogin(context: Context): Boolean {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getBoolean(AUTO_LOGIN, false)!!
        }
    }
}