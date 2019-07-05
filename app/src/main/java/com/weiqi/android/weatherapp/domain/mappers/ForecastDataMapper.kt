package com.weiqi.android.weatherapp.domain.mappers

import com.weiqi.android.weatherapp.data.server.Forecast
import com.weiqi.android.weatherapp.data.server.ForecastResult
import com.weiqi.android.weatherapp.domain.model.ForecastList
import java.text.DateFormat
import java.util.*

import com.weiqi.android.weatherapp.domain.model.Forecast as ModelForecast

class ForecastDataMapper {


    fun convertFormDataModel(zipCode:Long,forecast: ForecastResult): ForecastList = with(forecast){
        return ForecastList(
            zipCode,
            city.name,
            city.country,
            convertForecastListToDomain(list)
        )
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        return list.map {
            convertForecastItemToDomain(it)
        }
    }

    private fun convertForecastItemToDomain(forecast: Forecast): ModelForecast = with(forecast){
        return ModelForecast(
            dt * 1000,
            weather[0].description,
            temp.max.toInt(),
            temp.min.toInt(),
            generateIconUrl(weather[0].icon)
        )
    }

    private fun generateIconUrl(iconCode: String): String {
        return "http://openweathermap.org/img/w/$iconCode.png"
    }

}