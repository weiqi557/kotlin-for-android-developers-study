package com.weiqi.android.weatherapp.domain.commands

import com.weiqi.android.weatherapp.data.server.ForecastRequest
import com.weiqi.android.weatherapp.domain.mappers.ForecastDataMapper
import com.weiqi.android.weatherapp.domain.model.ForecastList

class RequestForecastCommand (val zipCode:Long):Command<ForecastList>{
    override fun excute(): ForecastList {
        val foreRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFormDataModel(zipCode,foreRequest.execute())
    }
}