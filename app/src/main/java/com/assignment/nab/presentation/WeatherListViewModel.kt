package com.assignment.nab.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.model.Result
import com.assignment.model.WeatherModel
import com.assignment.nab.domain.usecase.GetWeatherUseCase
import com.assignment.base.reactive.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherListViewModel @Inject constructor(private val getWeatherUseCase: GetWeatherUseCase) :
    ViewModel() {

    private val loading = com.assignment.base.reactive.SingleLiveEvent<Boolean>()
    private val listWeather = MutableLiveData<List<WeatherModel>>()
    private val error = com.assignment.base.reactive.SingleLiveEvent<String>()

    val loadingLiveData: LiveData<Boolean> = loading
    val listWeatherLiveData: LiveData<List<WeatherModel>> = listWeather
    val errorLiveData: LiveData<String> = error

    fun getListWeather(cityName: String) {
        viewModelScope.launch {
            loading.value = true
            when (val result = getWeatherUseCase(cityName)) {
                is Result.Success -> {
                    listWeather.value = result.data
                }
                is Result.Error -> {
                    error.value = result.error
                }
            }
            loading.value = false
        }

    }
}