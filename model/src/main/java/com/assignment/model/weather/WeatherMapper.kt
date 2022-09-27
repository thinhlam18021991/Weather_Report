package com.assignment.model.weather

import com.assignment.networkmodel.ForecastRemote
import com.assignment.utils.DateUtils

object WeatherMapper {
    fun toModel(forecastRemote: ForecastRemote): WeatherModel {
        val avgTem = ((forecastRemote.temp?.max ?: 0.0) +
                (forecastRemote.temp?.min ?: 0.0)) / 2.0
        return WeatherModel(
            date = if (forecastRemote.date == null) "" else
                DateUtils.convertLongToDateTimeForWeather(forecastRemote.date!!),
            description = forecastRemote.weatherList?.get(0)?.description.orEmpty(),
            pressure = forecastRemote.pressure ?: 0,
            humidity = forecastRemote.humidity ?: 0,
            avgTemperature = avgTem
        )
    }
}