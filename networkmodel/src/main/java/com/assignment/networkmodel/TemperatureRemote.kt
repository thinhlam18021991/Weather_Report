package com.assignment.networkmodel

import com.google.gson.annotations.SerializedName

data class TemperatureRemote(
    @SerializedName("day")
    val day: Double? = null,
)