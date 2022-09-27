package com.assignment

import com.assignment.utils.DateUtils
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class DateUtilsTest {

    @Test
    fun testConvertLongToDate()
    {
        val intput = 1664251200L
        val outputExpect = "Tue, 27 Sep 2022"
        val actualOutput = DateUtils.convertLongToDateTimeForWeather(intput)
        assertThat(actualOutput).isEqualTo(outputExpect)
    }
}