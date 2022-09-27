package com.assignment.model.weather

import com.assignment.networkmodel.ForecastRemote
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class WeatherModelMapperTest {

    @Test
    fun testMapperNetworkToModel()
    {
        val input = createForecastRemote()
        val outputExpect = createWeatherModel()
        val actualOutput = WeatherMapper.toModel(input)
        assertThat(actualOutput).isEqualTo(outputExpect)
    }

    @Test
    fun testMapperNetworkToModelWithAbsentData()
    {
        val input = ForecastRemote()
        val outputExpect = WeatherModel()
        val actualOutput = WeatherMapper.toModel(input)
        assertThat(actualOutput).isEqualTo(outputExpect)
    }
}