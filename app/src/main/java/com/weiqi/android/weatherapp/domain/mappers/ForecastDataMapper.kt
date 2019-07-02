package com.weiqi.android.weatherapp.domain.mappers

import com.weiqi.android.weatherapp.data.Forecast
import com.weiqi.android.weatherapp.data.ForecastResult
import com.weiqi.android.weatherapp.domain.model.ForecastList
import java.text.DateFormat
import java.util.*

import com.weiqi.android.weatherapp.domain.model.Forecast as ModelForecast

class ForecastDataMapper {


    fun converFormDataModel(forecast:ForecastResult):ForecastList{
        return ForecastList(
            forecast.city.name,
            forecast.city.country,
            convertForecastListToDomain(forecast.list)
        )
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        return list.map {
            convertForecastItemToDomain(it)
        }
    }

    private fun convertForecastItemToDomain(forecast: Forecast):ModelForecast {
        return ModelForecast(
            convertDate(forecast.dt),
            forecast.weather[0].description,
            forecast.temp.max.toInt(),
            forecast.temp.min.toInt()
        )
    }

    private fun convertDate(date:Long):String{
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date * 1000)
    }

}