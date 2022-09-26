package com.assignment.model

import com.assignment.networkmodel.ForecastRemote
import com.assignment.networkmodel.WeatherRemote
import com.assignment.utils.DateUtils

object WeatherMapper {
    fun toModel(forecastRemote: ForecastRemote): WeatherModel {
        val avgTem = ((forecastRemote.temp?.max ?: 0.0) +
                (forecastRemote.temp?.min ?: 0.0)) / 2.0
        return WeatherModel(
            date = DateUtils.convertLongToDateTimeForWeather(forecastRemote.date ?: 0),
            description = forecastRemote.weatherList?.get(0)?.description.orEmpty(),
            pressure = forecastRemote.pressure ?: 0,
            humidity = forecastRemote.humidity ?: 0,
            avgTemperature = avgTem
        )
    }
}