package com.weiqi.android.weatherapp.domain.commands

import com.weiqi.android.weatherapp.data.ForecastRequest
import com.weiqi.android.weatherapp.domain.mappers.ForecastDataMapper
import com.weiqi.android.weatherapp.domain.model.ForecastList

class RequestForecastCommand (val zipCode:String):Command<ForecastList>{
    override fun excute(): ForecastList {
        val foreRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().converFormDataModel(foreRequest.execute())
    }
}