package com.weiqi.android.weatherapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.weiqi.android.weatherapp.R
import com.weiqi.android.weatherapp.domain.model.Forecast
import com.weiqi.android.weatherapp.domain.model.ForecastList
import com.weiqi.android.weatherapp.utils.cxt
import org.jetbrains.anko.find

class ForecastListAdapter(val weekForecast: ForecastList,val itemClick: OnClickItemListener) : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.cxt).inflate(R.layout.item_forecast, parent, false),itemClick)
    }

    override fun getItemCount(): Int = weekForecast.size()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    class ViewHolder(itemView: View,val itemClick:OnClickItemListener) : RecyclerView.ViewHolder(itemView){

        private val mImgIcon:ImageView
        private val mTvDate:TextView
        private val mTvDesc:TextView
        private val mTvHigh:TextView
        private val mTvLow:TextView

        init {
            mImgIcon = itemView.find(R.id.icon)
            mTvDate = itemView.find(R.id.date)
            mTvDesc = itemView.find(R.id.desc)
            mTvHigh = itemView.find(R.id.high)
            mTvLow = itemView.find(R.id.low)
        }

        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                Glide.with(itemView).load(iconUrl).into(mImgIcon)
                mTvDate.text = date
                mTvDesc.text = description
                mTvHigh.text = "$high"
                mTvLow.text = "$low"
                itemView.setOnClickListener { itemClick(this) }
            }
        }

    }

    interface OnClickItemListener {
        operator fun invoke(forecast: Forecast)
    }
}