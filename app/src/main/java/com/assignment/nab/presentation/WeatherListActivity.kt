package com.assignment.nab.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
class WeatherListActivity : AppCompatActivity() {


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

    private fun initView(key: String) {
        binding.run {
            val decoration = SeparatorDecoration(
                this@WeatherListActivity,
                R.color.divider, 1.5f
            )

            edtInput.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    btnGetWeather.isEnabled = s != null && s.length >= SEARCH_KEYWORD_SIZE
                }

                override fun afterTextChanged(s: Editable?) {}
            })

            listWeather.addItemDecoration(decoration)
            listWeather.adapter = adapter
            btnGetWeather.setOnClickListener {
                viewModel.getListWeather(edtInput.text.toString())
            }
            if (key.isNotBlank()) {
                edtInput.setText(key)
                viewModel.getListWeather(key)
            }
        }


    }

    private fun registerObservable() {
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

    // Save state in case on dont keep activity setting on phone
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.onSaveInstanceState(outState)
    }


    // We can use safetynet to check
    // safetynet is google API
    // it request network and api key
    // rootbear can check root device
    // but it miss some cases
    private fun checkRootDevice() {
        val rootBeer = RootBeer(this)
        if (rootBeer.isRooted) {
            this.createAlertDialog(getString(R.string.error_root_detected)) {
                finish()
            }
        }
    }

    companion object {
        private const val SEARCH_KEYWORD_SIZE = 3

    }
}