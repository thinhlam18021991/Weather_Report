package com.assignment.nab.presentation

import android.os.Looper
import androidx.lifecycle.Observer
import com.assignment.model.weather.WeatherModel
import com.assignment.nab.domain.usecase.GetWeatherUseCase
import com.google.common.truth.Truth
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.Result
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import java.util.*


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
            val weatherListObs: Observer<List<WeatherModel>> = mockk()
            val showLoadingObs: Observer<Unit> = mockk()
            val hideLoadingObs: Observer<Unit> = mockk()
            weatherListViewModel.loadingLiveData.observeForever(showLoadingObs)
            weatherListViewModel.listWeatherLiveData.observeForever(weatherListObs)
            weatherListViewModel.hideLoadingLiveData.observeForever(hideLoadingObs)
            coEvery { getWeatherUseCase("city") }.returns(com.assignment.model.Result.Success(mockk()))
            weatherListViewModel.getListWeather("city")
            every { showLoadingObs.onChanged(Unit) } answers   { nothing }
            every { weatherListObs.onChanged(any()) } answers   { nothing }
            every { hideLoadingObs.onChanged(Unit) } answers   { nothing }
            Shadows.shadowOf(Looper.getMainLooper()).idle()
            verifySequence {
                showLoadingObs.onChanged(Unit)
                weatherListObs.onChanged(any())
                hideLoadingObs.onChanged(Unit)
            }
        }
    }

    @Test
    fun loadWeather_failed() {
        runBlocking {
            val errorObs: Observer<String> = mockk()
            val showLoadingObs: Observer<Unit> = mockk()
            val hideLoadingObs: Observer<Unit> = mockk()
            weatherListViewModel.loadingLiveData.observeForever(showLoadingObs)
            weatherListViewModel.errorLiveData.observeForever(errorObs)
            weatherListViewModel.hideLoadingLiveData.observeForever(hideLoadingObs)
            coEvery { getWeatherUseCase("city") }.returns(com.assignment.model.Result.Error(""))
            weatherListViewModel.getListWeather("city")
            every { showLoadingObs.onChanged(Unit) } answers   { nothing }
            every { errorObs.onChanged(any()) } answers   { nothing }
            every { hideLoadingObs.onChanged(Unit) } answers   { nothing }
            Shadows.shadowOf(Looper.getMainLooper()).idle()
            verifySequence {
                showLoadingObs.onChanged(Unit)
                errorObs.onChanged(any())
                hideLoadingObs.onChanged(Unit)
            }
        }
    }
}