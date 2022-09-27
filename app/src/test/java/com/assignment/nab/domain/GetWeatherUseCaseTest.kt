package com.assignment.nab.domain

import com.assignment.model.weather.WeatherModel
import com.assignment.nab.domain.repository.WeatherRepository
import com.assignment.nab.domain.usecase.GetWeatherUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetWeatherUseCaseTest {

    @MockK
    private lateinit var weatherRepository: WeatherRepository

    private lateinit var getWeatherUseCase: GetWeatherUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        getWeatherUseCase = GetWeatherUseCase(weatherRepository)
    }

    @Test
    fun test_get_weather_use_case_call_to_right_repository() {
        val listWeather: List<WeatherModel> = mockk()
        // For success case
        coEvery { weatherRepository.getWeather("city") }.returns(
            com.assignment.model.Result.Success(listWeather),
        )
        runBlocking {
            getWeatherUseCase("city")
            coVerify { weatherRepository.getWeather("city") }
        }

        // For failed case
        coEvery { weatherRepository.getWeather("failed") }.returns(
            com.assignment.model.Result.Error("Throwable()"),
        )
        runBlocking {
            getWeatherUseCase("failed")
            coVerify { weatherRepository.getWeather("failed") }
        }
    }
}