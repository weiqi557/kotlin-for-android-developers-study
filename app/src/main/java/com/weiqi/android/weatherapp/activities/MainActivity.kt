package com.weiqi.android.weatherapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.weiqi.android.weatherapp.R
import com.weiqi.android.weatherapp.adapters.ForecastListAdapter
import com.weiqi.android.weatherapp.domain.commands.RequestForecastCommand
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activitiy)

        val forecastList = findViewById<RecyclerView>(R.id.recyclerView)
        forecastList.layoutManager = LinearLayoutManager(this)


        val url = "http://api.openweathermap.org/data/2.5/forecast/daily?" +
                "APPID=15646a06818f61f7b8d7823ca833e1ce&q=94043&mode=json&units=metric&cnt=7"

        doAsync{
            val result = RequestForecastCommand("94043").excute()
            uiThread {
                forecastList.adapter = ForecastListAdapter(result)
            }
        }
    }
}