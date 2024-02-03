package com.ebraratabay.aeskgo.services

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferencesService @Inject constructor(
    val sharedPreferences: SharedPreferences,

    ) {

    fun getStringFromSP(dataKey: String): String? {
        return sharedPreferences.getString(dataKey, "0")

    }

    fun getIntFromSP(dataKey: String): Int {
        return sharedPreferences.getInt(dataKey, 0)

    }

    fun editIntFromSP(dataKey: String, intData: Int) {
        sharedPreferences.edit().putInt(dataKey, intData)

    }

    fun editStringFromSP(dataKey: String, stringData: String) {
        sharedPreferences.edit().putString(dataKey, stringData)
    }
}