package com.assignment.networkmodel

import com.google.gson.annotations.SerializedName

data class ForecastRemote(
    @SerializedName("dt")
    val date: Long? = null,
    @SerializedName("temp")
    val temp: TemperatureRemote? = null,
    @SerializedName("pressure")
    val pressure: Int? = null,
    @SerializedName("humidity")
    val humidity: Int? = null,
    @SerializedName("weather")
    val weatherList: List<ChildWeatherRemote>? = null,
)
