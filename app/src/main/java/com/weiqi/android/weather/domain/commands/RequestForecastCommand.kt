package com.weiqi.android.weather.domain.commands

import com.weiqi.android.weather.data.ForecastRequest
import com.weiqi.android.weather.domain.mappers.ForecastDataMapper
import com.weiqi.android.weather.domain.model.ForecastList

class RequestForecastCommand(val zipCode: String) : Command<ForecastList> {
    override fun execute(): ForecastList {
        return ForecastDataMapper().convertFromDataModel(
            ForecastRequest(zipCode).execute()
        )
    }
}