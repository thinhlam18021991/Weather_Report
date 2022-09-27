package com.assignment.model

import java.io.Serializable

abstract class Model : Serializable {
    protected open val serialVersionUID: Long = 1L
}