package com.assignment.nab.domain.usecase

import com.assignment.model.Result
import com.assignment.model.WeatherModel
import com.assignment.nab.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend operator fun invoke(city: String): Result<List<WeatherModel>> =
        weatherRepository.getWeather(city)

}