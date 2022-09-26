package com.assignment.network.networkapi

import retrofit2.Retrofit

inline fun <reified T> Retrofit.createRestService(): T {
    return create(T::class.java) as T
}