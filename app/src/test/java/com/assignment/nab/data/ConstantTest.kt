package com.assignment.nab.data

import com.assignment.nab.data.utils.Constant
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ConstantTest {

    @Test
    fun testRequestConstant()
    {
        assertThat(Constant.NUMBER_O_FDAY).isEqualTo(7)
        assertThat(Constant.UNIT).isEqualTo("Metric")
        assertThat(Constant.SUCCESS_CODE).isEqualTo("200")

    }
}