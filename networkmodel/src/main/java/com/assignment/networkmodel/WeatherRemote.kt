package com.assignment.networkmodel

import com.google.gson.annotations.SerializedName

data class WeatherRemote (
    @SerializedName("list")
    val forecastList: List<ForecastRemote>?
): BaseResponse()