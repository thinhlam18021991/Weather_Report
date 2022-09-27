package com.assignment.model.base

fun Any.getSimpleClassName(): String = this::class.simpleName.orEmpty()
