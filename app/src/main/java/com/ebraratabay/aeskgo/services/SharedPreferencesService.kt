package com.ebraratabay.aeskgo.services


import android.app.Application
import android.content.Context
import android.content.SharedPreferences


class SharedPreferencesService( val dataKey: String, val context: Context) {
    private lateinit var sharedPreferences: SharedPreferences

    init {
        provideSharedPreferences(context)
    }

    fun provideSharedPreferences(context: Context) {

        sharedPreferences =context.getSharedPreferences(dataKey, Context.MODE_PRIVATE)
    }


    fun getStringFromSP(): String? {
        return sharedPreferences.getString(dataKey, "0")

    }

    fun getIntFromSP(): Int {
        return sharedPreferences.getInt(dataKey, 0)

    }

    fun editIntFromSP(intData: Int) {
        sharedPreferences.edit().putInt(dataKey, intData)

    }

    fun editStringFromSP(stringData: String) {
        sharedPreferences.edit().putString(dataKey, stringData)
    }
}