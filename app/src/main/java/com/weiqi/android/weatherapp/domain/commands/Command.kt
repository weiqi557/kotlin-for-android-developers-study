package com.weiqi.android.weatherapp.domain.commands

interface Command <T>{
    fun execute():T
}