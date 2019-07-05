package com.weiqi.android.weatherapp.data.server

import com.weiqi.android.weatherapp.data.db.ForecastDb
import com.weiqi.android.weatherapp.domain.datasource.ForecastDataSource
import com.weiqi.android.weatherapp.domain.model.ForecastList

class ForecastServer(
    val dataMapper: ServerDataMapper = ServerDataMapper(),
    val forecastDb: ForecastDb = ForecastDb()
) : ForecastDataSource {
    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }
}