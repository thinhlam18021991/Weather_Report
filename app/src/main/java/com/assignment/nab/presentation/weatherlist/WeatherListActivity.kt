package com.assignment.nab.presentation.weatherlist

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.assignment.nab.R
import com.assignment.security.SecurityHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WeatherListActivity: AppCompatActivity() {


    private val viewModel by viewModels<WeatherListViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_list)
        viewModel.getListWeather("saigon")
    }

}