package com.assignment.model.weather

import com.assignment.networkmodel.ChildWeatherRemote
import com.assignment.networkmodel.ForecastRemote
import com.assignment.networkmodel.TemperatureRemote

fun createWeatherModel() = WeatherModel(
    date = "Tue, 27 Sep 2022",
    avgTemperature = 32.5,
    pressure = 1008,
    humidity = 56,
    description = "description"
)

fun createChildWeatherRemote() = ChildWeatherRemote(
    description = "description"
)

fun createTemperatureRemote() = TemperatureRemote (
    min = 30.0,
    max = 35.0,
)

fun createForecastRemote() = ForecastRemote(
    date = 1664251200,
    temp = createTemperatureRemote(),
    pressure = 1008,
    humidity = 56,
    weatherList = listOf(createChildWeatherRemote())
)