package com.weiqi.android.weatherapp.domain.datasource

import com.weiqi.android.weatherapp.domain.model.ForecastList

interface ForecastDataSource{
    fun requestForecastByZipCode(zipCode:Long,date:Long):ForecastList?
}