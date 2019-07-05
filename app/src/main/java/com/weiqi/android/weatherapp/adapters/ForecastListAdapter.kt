package com.weiqi.android.weatherapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.weiqi.android.weatherapp.R
import com.weiqi.android.weatherapp.domain.model.Forecast
import com.weiqi.android.weatherapp.domain.model.ForecastList
import com.weiqi.android.weatherapp.extensions.cxt
import kotlinx.android.synthetic.main.item_forecast.view.*
import java.text.DateFormat
import java.util.*

class ForecastListAdapter(val weekForecast: ForecastList, val itemClick: (Forecast) -> Unit) :
    RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.cxt).inflate(R.layout.item_forecast, parent, false), itemClick)
    }

    override fun getItemCount(): Int = weekForecast.size()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    class ViewHolder(itemView: View, val itemClick: (Forecast) -> Unit) : RecyclerView.ViewHolder(itemView) {

        fun bindForecast(forecast: Forecast) {
            with(forecast) {

                Glide.with(itemView).load(iconUrl).into(itemView.icon)
                itemView.date.text = convertDate(date)
                itemView.desc.text = description
                itemView.high.text = "$high"
                itemView.low.text = "$low"
                itemView.setOnClickListener { itemClick(this) }
            }
        }

        private fun convertDate(date: Long): String {
            val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
            return df.format(date)
        }

    }

    interface OnClickItemListener {
        operator fun invoke(forecast: Forecast)
    }
}