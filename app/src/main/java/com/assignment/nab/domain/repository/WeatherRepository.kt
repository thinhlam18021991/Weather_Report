package com.assignment.nab.domain.repository

import com.assignment.model.Result
import com.assignment.model.WeatherModel


interface WeatherRepository {

    suspend fun getWeather(city: String): Result<List<WeatherModel>>
}