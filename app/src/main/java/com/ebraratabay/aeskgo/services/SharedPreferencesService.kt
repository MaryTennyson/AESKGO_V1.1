package com.ebraratabay.aeskgo.services


import android.content.Context
import android.content.SharedPreferences


class SharedPreferencesService(val dataKey: String, val context: Context) {

    var sharedPreferences: SharedPreferences = context.getSharedPreferences(dataKey, Context.MODE_PRIVATE)


    fun getStringFromSP(): String {
        val userID = sharedPreferences.getString(dataKey, "0")!!
        println(userID)
        return userID

    }

    fun editStringFromSP(stringData: String) {
        sharedPreferences.edit().putString(dataKey, stringData).apply()
    }


}