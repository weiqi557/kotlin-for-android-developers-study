package com.weiqi.android.weatherapp.data.db

import com.weiqi.android.weatherapp.domain.datasource.ForecastDataSource
import com.weiqi.android.weatherapp.domain.model.ForecastList
import com.weiqi.android.weatherapp.extensions.clear
import com.weiqi.android.weatherapp.extensions.toVarargArray
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class ForecastDb(
    val forecastHelper: ForecastDbHelper = ForecastDbHelper.instance,
    val dataMapper: DbDataMapper = DbDataMapper()
) : ForecastDataSource {

    override fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastHelper.use {

        val dailyRequest = "${DayForecastTable.CITY_ID} = ? AND ${DayForecastTable.DATE} >= ?"
        val dailyForecast = select(DayForecastTable.NAME)
            .whereSimple(dailyRequest, zipCode.toString(), date.toString())
            .parseList(object : MapRowParser<DayForecast> {
                override fun parseRow(columns: Map<String, Any?>): DayForecast {
                    return DayForecast(columns as HashMap)
                }
            })

        val city = select(CityForecastTable.NAME)
            .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
            .parseOpt(object : MapRowParser<CityForecast> {
                override fun parseRow(columns: Map<String, Any?>): CityForecast {
                    return CityForecast(columns as HashMap,dailyForecast)
                }
            })
        if (city != null) dataMapper.convertToDomain(city) else null
    }

    fun saveForecast(forecast: ForecastList) = forecastHelper.use {
        clear(CityForecastTable.NAME)
        clear(DayForecastTable.NAME)

        with(dataMapper.convertFromDomain(forecast)) {
            insert(CityForecastTable.NAME, *map.toVarargArray())
            dailyForecast.forEach { insert(DayForecastTable.NAME, *it.map.toVarargArray()) }
        }
    }

}