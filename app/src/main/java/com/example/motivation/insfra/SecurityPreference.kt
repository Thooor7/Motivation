package com.example.motivation.insfra

import android.content.Context
import android.content.SharedPreferences
import com.example.motivation.R
import com.example.motivation.insfra.MotivationConstants.KEY.EMPTY
import com.example.motivation.insfra.MotivationConstants.KEY.MOTIVATION

class SecurityPreference(context: Context) {

    private val security: SharedPreferences =
        context.getSharedPreferences(MOTIVATION, Context.MODE_PRIVATE)


    fun storeString(key: String, str: String) {
        security.edit().putString(key, str).apply()
    }

    fun getString(key: String): String {
        return security.getString(key, EMPTY) ?: EMPTY
    }
}