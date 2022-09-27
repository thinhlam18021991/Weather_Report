package com.assignment.nab.presentation

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.assignment.base.divider.SeparatorDecoration
import com.assignment.nab.R
import com.assignment.nab.databinding.ActivityWeatherListBinding
import com.assignment.nab.presentation.adapter.WeatherListAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


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

    }

    private fun initView(key: String)
    {
        val decoration = SeparatorDecoration(this, R.color.black, 1.5f)
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
        binding.btnSpeak.setOnClickListener {
            promptSpeechInput()
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
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
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

    // Speak to text
    private fun promptSpeechInput() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(
            RecognizerIntent.EXTRA_PROMPT,
            getString(R.string.speak)
        )
        try {
            startActivityForResult(intent, SPEAK_TO_TEXT_REQUEST)
        } catch (a: ActivityNotFoundException) {
            Snackbar.make(binding.root, getString(R.string.speak_not_support), Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SPEAK_TO_TEXT_REQUEST) {
            if (resultCode == RESULT_OK && data != null) {
                val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                val text : String = result?.get(0) ?: ""
                if (text.isNotBlank()) {
                    binding.edtInput.setText(text)
                    viewModel.getListWeather(text)
                }
            }
        }
    }

    companion object {
        private const val SPEAK_TO_TEXT_REQUEST = 100
    }
}