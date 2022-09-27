package com.assignment.nab.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.base.viewholder.BaseViewHolder
import com.assignment.model.weather.WeatherModel

class WeatherListAdapter(): RecyclerView.Adapter<BaseViewHolder>() {

    private var weatherList: List<WeatherModel>? = null

    fun serData(list: List<WeatherModel>) {
        weatherList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return WeatherViewHolder(parent)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        (holder as WeatherViewHolder).bind(weatherList!![position])
    }

    override fun getItemCount(): Int = weatherList?.size ?: 0
}