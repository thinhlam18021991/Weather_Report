package com.assignment.model

data class WeatherModel (val date: String = "",
                         val avgTemperature: Double = 0.0,
                         val pressure: Int = 0,
                         val humidity: Int = 0,
                         val description: String = "")