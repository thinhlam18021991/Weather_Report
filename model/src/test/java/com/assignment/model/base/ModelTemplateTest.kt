package com.assignment.model.base

import com.assignment.model.Model
import com.google.common.truth.Truth
import org.junit.Test
import kotlin.reflect.full.declaredMemberProperties

abstract class ModelTemplateTest<T : Model> {

    abstract fun provideModelData(): T

    abstract fun provideDataModelFieldNumber(): Int

    @Test
    open fun testDataModelFieldNumber() {
        val input = provideDataModelFieldNumber()
        val expected = provideModelData()::class.declaredMemberProperties.size
        val outputClazz = provideModelData().getSimpleClassName()
        Truth.assertWithMessage(
            "testNumberOfDataModelField: " +
                    "input $input does not match with $expected, model $outputClazz",
        ).that(input).isEqualTo(expected)
    }

}