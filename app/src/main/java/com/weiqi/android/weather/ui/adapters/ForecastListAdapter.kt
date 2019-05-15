package com.weiqi.android.weather.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.weiqi.android.weather.R
import com.weiqi.android.weather.domain.model.Forecast
import com.weiqi.android.weather.domain.model.ForecastList
import org.jetbrains.anko.find

class ForecastListAdapter(val weekForecast: ForecastList,val itemClick: OnItemClickListener) :
    RecyclerView.Adapter<ForecastListAdapter.ForeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForeViewHolder =
        ForeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_item,parent,false),itemClick)

    override fun getItemCount(): Int = weekForecast.size()

    override fun onBindViewHolder(holder: ForeViewHolder, position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    class ForeViewHolder(val view: View, val itemClick: OnItemClickListener) : RecyclerView.ViewHolder(view) {
        private val iconView: ImageView
        private val dateView: TextView
        private val descriptionView: TextView
        private val maxTemperatureView: TextView
        private val minTemperatureView: TextView

        init {
            iconView = view.find(R.id.icon)
            dateView = view.find(R.id.date)
            descriptionView = view.find(R.id.description)
            maxTemperatureView = view.find(R.id.maxTemperature)
            minTemperatureView = view.find(R.id.minTemperature)
        }

        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                Glide.with(view.context).load(iconUrl).into(iconView)
                dateView.text = date
                descriptionView.text = description
                maxTemperatureView.text = "$high°"
                minTemperatureView.text = "$low°"
                view.setOnClickListener {
                    itemClick(forecast)
                }
            }
        }
    }

    interface OnItemClickListener {
        operator fun invoke(forecast: Forecast)
    }
}
