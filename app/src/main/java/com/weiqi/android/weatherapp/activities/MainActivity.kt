package com.weiqi.android.weatherapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.weiqi.android.weatherapp.R
import com.weiqi.android.weatherapp.adapters.ForecastListAdapter
import com.weiqi.android.weatherapp.domain.commands.RequestForecastCommand
import kotlinx.android.synthetic.main.main_activitiy.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activitiy)

        forecastList.layoutManager = LinearLayoutManager(this)

        doAsync {
            val result = RequestForecastCommand(94043).excute()
            uiThread {
                forecastList.adapter = ForecastListAdapter(result) { forecast ->
                    toast(forecast.toString())
                }
            }
        }


    }
}