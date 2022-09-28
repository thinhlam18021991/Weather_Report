package com.assignment.nab.presentation

import androidx.lifecycle.Observer
import com.assignment.model.weather.WeatherModel
import com.assignment.nab.domain.usecase.GetWeatherUseCase
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class WeatherListViewModelTest {

    @MockK
    private lateinit var getWeatherUseCase: GetWeatherUseCase

    private lateinit var weatherListViewModel: WeatherListViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        weatherListViewModel =
            WeatherListViewModel(
                getWeatherUseCase)
    }

    @Test
    fun loadWeather_success() {
        runBlocking {
            val weatherListObs: Observer<List<WeatherModel>> = mockk(relaxed = true)
            val loadingObs: Observer<Boolean> = mockk(relaxed = true)
            weatherListViewModel.loadingLiveData.observeForever(loadingObs)
            weatherListViewModel.listWeatherLiveData.observeForever(weatherListObs)
            coEvery { getWeatherUseCase("city") }.returns(com.assignment.model.Result.Success(mockk()))
            weatherListViewModel.getListWeather("city")
            verifySequence {
                loadingObs.onChanged(true)
                weatherListObs.onChanged(any())
                loadingObs.onChanged(false)
            }
        }
    }

    @Test
    fun loadWeather_failed() {
        runBlocking {
            val errorObs: Observer<String> = mockk(relaxed = true)
            val loadingObs: Observer<Boolean> = mockk(relaxed = true)
            weatherListViewModel.loadingLiveData.observeForever(loadingObs)
            weatherListViewModel.errorLiveData.observeForever(errorObs)
            coEvery { getWeatherUseCase("city") }.returns(com.assignment.model.Result.Error(""))
            weatherListViewModel.getListWeather("city")
            verifySequence {
                loadingObs.onChanged(true)
                errorObs.onChanged(any())
                loadingObs.onChanged(false)
            }
        }
    }
}