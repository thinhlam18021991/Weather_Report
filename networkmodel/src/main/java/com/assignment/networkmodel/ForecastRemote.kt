package com.assignment.networkmodel

import com.google.gson.annotations.SerializedName

data class ForecastRemote(
    @SerializedName("dt")
    val date: Long?,
    @SerializedName("temp")
    val temp: TemperatureRemote?,
    @SerializedName("pressure")
    val pressure: Int?,
    @SerializedName("humidity")
    val humidity: Int?,
    @SerializedName("weather")
    val weatherList: List<ChildWeatherRemote>?,
)
