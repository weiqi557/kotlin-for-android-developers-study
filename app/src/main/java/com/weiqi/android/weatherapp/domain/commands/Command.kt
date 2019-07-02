package com.weiqi.android.weatherapp.domain.commands

interface Command <T>{
    fun excute():T
}