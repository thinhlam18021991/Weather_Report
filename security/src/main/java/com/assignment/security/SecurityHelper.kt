package com.assignment.security

object SecurityHelper {

    init {
        System.loadLibrary("keys")
    }

    external fun appId(): String

}