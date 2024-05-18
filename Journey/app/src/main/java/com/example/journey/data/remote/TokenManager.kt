package com.example.journey.data.remote

import android.content.Context
import android.content.SharedPreferences
import com.example.journey.MainActivity

object TokenManager {
    private const val PREFS_NAME = "auth"
    private const val TOKEN_KEY = "token"

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: MainActivity) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun getToken(): String {
        return sharedPreferences.getString(TOKEN_KEY, "") ?: ""
    }

    fun setToken(token: String) {
        sharedPreferences.edit().putString(TOKEN_KEY, token).apply()
    }
}