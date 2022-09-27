package com.assignment.nab.presentation.adapter

import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import com.assignment.base.viewholder.BaseViewHolder
import com.assignment.model.weather.WeatherModel
import com.assignment.nab.R
import com.assignment.nab.databinding.ItemWeatherBinding

class WeatherViewHolder(
    parent: ViewGroup) : BaseViewHolder(parent, R.layout.item_weather)  {

    @VisibleForTesting
    val binding = ItemWeatherBinding.bind(itemView)

    fun bind(weatherModel: WeatherModel) {
        binding.txtDate.text = context.getString(R.string.date, weatherModel.date)
        binding.txtTemperature.text = context.getString(R.string.temperature,
            weatherModel.avgTemperature)
        binding.txtHumidity.text = context.getString(R.string.humidity, weatherModel.humidity)
        binding.txtPressure.text = context.getString(R.string.pressure, weatherModel.pressure)
        binding.txtDesciption.text = context.getString(R.string.description, weatherModel.description)
    }
}