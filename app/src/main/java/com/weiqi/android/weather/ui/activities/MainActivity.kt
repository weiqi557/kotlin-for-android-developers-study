package com.weiqi.android.weather.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.weiqi.android.weather.R
import com.weiqi.android.weather.domain.commands.RequestForecastCommand
import com.weiqi.android.weather.domain.model.Forecast
import com.weiqi.android.weather.ui.adapters.ForecastListAdapter
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val forecastList = find<RecyclerView>(R.id.recycler_view)
        forecastList.layoutManager = LinearLayoutManager(this)

        doAsync {
            val result = RequestForecastCommand("94043").execute()
            uiThread {
                forecastList.adapter = ForecastListAdapter(result,object:ForecastListAdapter.OnItemClickListener{
                    override fun invoke(forecast: Forecast) {
                        toast(forecast.date)
                    }
                })
            }
        }
    }


}