package com.assignment.nab.presentation.adapter

import android.app.Activity
import android.view.ViewGroup
import com.assignment.model.weather.WeatherModel
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import com.assignment.nab.R


@RunWith(RobolectricTestRunner::class)
class WeatherViewHolderTest {

    private lateinit var activityController: ActivityController<Activity>
    private lateinit var activity: Activity
    private lateinit var viewHolder: WeatherViewHolder

    @Before
    fun setUp() {
        activityController = Robolectric.buildActivity(Activity::class.java)
        activity = activityController.get()

        val parentView = activity.findViewById<ViewGroup>(android.R.id.content)
        viewHolder = WeatherViewHolder(parentView)
    }

    @Test
    fun verifyViewContent() {
        val weather = WeatherModel(
            date = "Tue, 27 Sep 2022",
            avgTemperature = 32.5,
            pressure = 1008,
            humidity = 56,
            description = "description"
        )
        // not last item
        viewHolder.bind(weather)
        viewHolder.binding.run {
            assertThat(txtDate.text).isEqualTo("Date: Tue, 27 Sep 2022")
            assertThat(txtTemperature.text).isEqualTo(activity.baseContext.getString(R.string.temperature, 32.5))
            assertThat(txtPressure.text).isEqualTo("Pressure: 1008")
            assertThat(txtHumidity.text).isEqualTo("Humidity: 56%")
            assertThat(txtDesciption.text).isEqualTo("Description: description")

        }

    }
}