package com.weiqi.android.weatherapp.data

import android.util.Log
import java.net.URL

class Request (val url:String){

    public fun run() {
        val forecastJson = URL(url).readText()
        Log.d(javaClass.simpleName,forecastJson)
    }
}