package com.weiqi.android.weatherapp.domain.datasource

import com.weiqi.android.weatherapp.data.db.ForecastDb
import com.weiqi.android.weatherapp.data.server.ForecastServer
import com.weiqi.android.weatherapp.domain.model.ForecastList
import com.weiqi.android.weatherapp.extensions.firstResult
import java.util.*

class ForecastProvider(val sources: List<ForecastDataSource> = SOURCES) {

    companion object {
        val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCES = listOf(ForecastDb(), ForecastServer())
    }

    fun requestByZipCode(zipCode: Long, days: Int): ForecastList =
        sources.firstResult { requestSource(it, days, zipCode) }

    private fun requestSource(source: ForecastDataSource, days: Int, zipCode: Long): ForecastList? {
        val res = source.requestForecastByZipCode(zipCode, todayTimeSpan())
        return if (res != null && res.size() >= days) res else null
    }

    private fun todayTimeSpan(): Long {
        val calender: Calendar = Calendar.getInstance()
        calender.set(Calendar.HOUR_OF_DAY, 0)
        calender.set(Calendar.MINUTE, 0)
        calender.set(Calendar.SECOND, 0)
        calender.set(Calendar.MILLISECOND, 0)
        return calender.timeInMillis
    }
}