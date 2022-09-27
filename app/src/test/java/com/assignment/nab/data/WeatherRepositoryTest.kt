package com.assignment.nab.data

import com.assignment.model.Result
import com.assignment.model.weather.WeatherModel
import com.assignment.nab.data.repository.WeatherRepositoryImpl
import com.assignment.nab.domain.repository.WeatherRepository
import com.assignment.nab.domain.usecase.GetWeatherUseCase
import com.assignment.network.apiservice.RestApiService
import com.assignment.networkmodel.ForecastRemote
import com.assignment.networkmodel.WeatherRemote
import com.assignment.security.SecurityNab
import com.google.common.truth.Truth.assertThat
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class WeatherRepositoryTest {

    @MockK
    private lateinit var restApiService: RestApiService

    @MockK
    private lateinit var securityNab: SecurityNab

    private lateinit var weatherRepository: WeatherRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        weatherRepository = WeatherRepositoryImpl(restApiService, securityNab)

        coEvery { securityNab.appId() }.returns("appId")
    }

    @Test
    fun test_get_weather_success() {
        // For success case
        val weatherRemote: WeatherRemote = mockk()
        coEvery { weatherRemote.code }.returns(
            "200"
        )
        coEvery { weatherRemote.forecastList }.returns(
            emptyList()
        )
        coEvery { restApiService.getWeather("city", 7, "appId", "Metric") }.returns(
           weatherRemote
        )
        runBlocking {
            val actualResult = weatherRepository.getWeather("city")
            assertThat(actualResult).isInstanceOf(Result.Success::class.java)
        }

    }

    @Test
    fun test_get_weather_failed_http_code() {
        // For failed case
        val weatherRemote: WeatherRemote = mockk()
        coEvery { weatherRemote.code }.returns(
            "400"
        )
        coEvery { weatherRemote.message }.returns(
            "error"
        )
        coEvery { restApiService.getWeather("city", 7, "appId", "Metric") }.returns(
            weatherRemote
        )
        runBlocking {
            val actualResult = weatherRepository.getWeather("city")
            assertThat(actualResult).isInstanceOf(Result.Error::class.java)
        }

    }

    @Test
    fun test_get_weather_failed_exaption() {
        // For failed case
        coEvery { restApiService.getWeather("city", 7, "appId", "Metric") } throws Exception()
        runBlocking {
            val actualResult = weatherRepository.getWeather("city")
            assertThat(actualResult).isInstanceOf(Result.Error::class.java)
        }

    }
}