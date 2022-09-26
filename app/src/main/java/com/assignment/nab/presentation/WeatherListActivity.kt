package com.assignment.nab.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assignment.base.divider.SeparatorDecoration
import com.assignment.nab.R
import com.assignment.nab.databinding.ActivityWeatherListBinding
import com.assignment.nab.presentation.adapter.WeatherListAdapter
import com.google.android.material.snackbar.Snackbar
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
        binding = ActivityWeatherListBinding.inflate(
            layoutInflater
        )
        setContentView(R.layout.activity_weather_list)
        setContentView(binding.root)
        registerObservable()
        initView()

    }

    private fun initView()
    {
        val decoration = SeparatorDecoration(this, R.color.black, 1.5f)
        binding.listWeather.addItemDecoration(decoration)
        binding.listWeather.adapter = adapter
        binding.btnGetWeather.setOnClickListener {
            binding.edtInput.clearFocus()
            viewModel.getListWeather(binding.edtInput.text.toString())
        }

    }

    private fun registerObservable()
    {
        viewModel.listWeatherLiveData.observe(this) {
            adapter.serData(it)
        }

        viewModel.errorLiveData.observe(this) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        }

        viewModel.loadingLiveData.observe(this) {
            if (it)
                binding.loading.visibility = View.VISIBLE
            else
                binding.loading.visibility = View.GONE

        }
    }
}