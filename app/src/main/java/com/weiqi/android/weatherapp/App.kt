package com.weiqi.android.weatherapp

import android.app.Application
import com.weiqi.android.weatherapp.utils.DelegatesExt

class App :Application(){

    companion object{
        var instance: App by DelegatesExt.notNullSingleValueVar()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}