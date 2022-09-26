package com.assignment.networkmodel

import com.google.gson.annotations.SerializedName

data class TemperatureRemote(
    @SerializedName("min")
    val min: Double?,
    @SerializedName("max")
    val max: Double?,
)