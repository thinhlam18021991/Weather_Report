package com.assignment.nab.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.assignment.base.dialog.createAlertDialog
import com.assignment.base.divider.SeparatorDecoration
import com.assignment.nab.R
import com.assignment.nab.databinding.ActivityWeatherListBinding
import com.assignment.nab.presentation.adapter.WeatherListAdapter
import com.scottyab.rootbeer.RootBeer
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WeatherListActivity: AppCompatActivity() {


    private val viewModel by viewModels<WeatherListViewModel>()

    private lateinit var binding: ActivityWeatherListBinding

    private val adapter: WeatherListAdapter by lazy {
        WeatherListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let {
            viewModel.onRestoreInstanceState(it)
        }
        binding = ActivityWeatherListBinding.inflate(
            layoutInflater
        )
        setContentView(R.layout.activity_weather_list)
        setContentView(binding.root)
        registerObservable()
        initView(viewModel.keySearch)
        checkRootDevice()
    }

    private fun initView(key: String)
    {
        val decoration = SeparatorDecoration(this, R.color.divider, 1.5f)
        binding.listWeather.addItemDecoration(decoration)
        binding.listWeather.adapter = adapter
        binding.btnGetWeather.setOnClickListener {
            binding.edtInput.clearFocus()
            viewModel.getListWeather(binding.edtInput.text.toString())
        }
        if (key.isNotBlank()) {
            binding.edtInput.setText(key)
            viewModel.getListWeather(key)
        }

    }

    private fun registerObservable()
    {
        viewModel.loadingLiveData.observe(this) {
            binding.loading.visibility = View.VISIBLE
        }

        viewModel.listWeatherLiveData.observe(this) {
            adapter.serData(it)
        }

        viewModel.errorLiveData.observe(this) {
            // re-message for meaningful
            createAlertDialog(
                getString(R.string.cannot_fetch_data)
            )
        }

        viewModel.hideLoadingLiveData.observe(this) {
            binding.loading.visibility = View.GONE
        }
    }

    // Save state in case randoom-deed or turn on dont keep activity setting on phone
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.onSaveInstanceState(outState)
    }


    // We can use safetynet to check
    // safetynet is google API
    // it request network and api key
    // rootbear can check root device
    // but it miss some cases
    private fun checkRootDevice()
    {
        val rootBeer = RootBeer(this)
        if (rootBeer.isRooted) {
            this.createAlertDialog(getString(R.string.error_root_detected)) {
                finish()
            }
        }
    }
    companion object {
        private const val SPEAK_TO_TEXT_REQUEST = 100
    }
}