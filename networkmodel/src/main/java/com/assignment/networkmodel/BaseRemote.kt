package com.assignment.networkmodel

import com.google.gson.annotations.SerializedName

open class BaseRemote(
    @SerializedName("cod")
    val code: String? = null,
    @SerializedName("message")
    val message: String? = null
)