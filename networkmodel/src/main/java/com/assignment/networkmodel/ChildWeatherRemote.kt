package com.assignment.networkmodel

import com.google.gson.annotations.SerializedName

data class ChildWeatherRemote(
    @SerializedName("description")
    val description: String?,
)