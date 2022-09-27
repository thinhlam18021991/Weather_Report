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

    private val loading = MutableLiveData<Unit>()
    private val hideLoading = MutableLiveData<Unit>()
    private var key: String = ""
    private val listWeather = MutableLiveData<List<WeatherModel>>()
    private val error = MutableLiveData<String>()

    val loadingLiveData: LiveData<Unit> = loading
    val hideLoadingLiveData: LiveData<Unit> = hideLoading
    val listWeatherLiveData: LiveData<List<WeatherModel>> = listWeather
    val errorLiveData: LiveData<String> = error

    val keySearch: String
        get() = key

    fun getListWeather(cityName: String) {
        key = keySearch
        viewModelScope.launch {
            loading.postValue(Unit)
            when (val result = getWeatherUseCase(cityName)) {
                is Result.Success -> {
                    listWeather.postValue(result.data)
                }
                is Result.Error -> {
                    error.postValue(result.error)
                }
            }
            hideLoading.postValue(Unit)
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