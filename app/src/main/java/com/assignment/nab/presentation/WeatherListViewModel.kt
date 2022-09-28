package com.assignment.nab.presentation

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.model.Result
import com.assignment.model.weather.WeatherModel
import com.assignment.nab.domain.usecase.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherListViewModel @Inject constructor(private val getWeatherUseCase: GetWeatherUseCase) :
    ViewModel() {

    private val loading = MutableLiveData<Boolean>()
    private var key: String = ""
    private val listWeather = MutableLiveData<List<WeatherModel>>()
    private val error = MutableLiveData<String>()

    val loadingLiveData: LiveData<Boolean> = loading
    val listWeatherLiveData: LiveData<List<WeatherModel>> = listWeather
    val errorLiveData: LiveData<String> = error

    val keySearch: String
        get() = key

    fun getListWeather(cityName: String) {
        key = keySearch
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

    fun onSaveInstanceState(outState: Bundle) {
        outState.putString(
            KEY_KEY_SEARCH,
            key,
        )
    }

    fun onRestoreInstanceState(saveState: Bundle) {
        key = saveState?.getString(KEY_KEY_SEARCH, "")
    }

    companion object {
        private const val KEY_KEY_SEARCH = "KEY_KEY_SEARCH"

    }
}