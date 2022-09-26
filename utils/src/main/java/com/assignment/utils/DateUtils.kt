package com.assignment.utils

import androidx.annotation.VisibleForTesting
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object DateUtils {

    @VisibleForTesting
    val DEFAULT_LOCALE: Locale = Locale.US

    // Date pattern as `Sun, 15 Mar 2019`
    private const val PATTERN_MMM_DD_YYYY = "EEE, dd MMM yyyy"

    private val formatterWeatherDate= SimpleDateFormat(PATTERN_MMM_DD_YYYY, DEFAULT_LOCALE)

    @JvmStatic
    fun convertLongToDateTimeForWeather(time: Long): String {
        val date = Date(TimeUnit.SECONDS.toMillis(time))
        return formatterWeatherDate.format(date)
    }

}