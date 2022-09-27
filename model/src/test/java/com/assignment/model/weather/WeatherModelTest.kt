package com.assignment.model.weather

import com.assignment.model.base.ModelTemplateTest
import org.junit.Assert
import org.junit.Test


class WeatherModelTest: ModelTemplateTest<WeatherModel>()  {

    @Test
    fun testDefaultConstructor() {
        Assert.assertEquals(
            WeatherModel(),
            WeatherModel(
                date = "",
                avgTemperature = 0.0,
                pressure = 0,
                humidity = 0,
                description = ""
            ),
        )
    }

    override fun provideModelData(): WeatherModel = createWeatherModel()

    override fun provideDataModelFieldNumber(): Int = 5

}