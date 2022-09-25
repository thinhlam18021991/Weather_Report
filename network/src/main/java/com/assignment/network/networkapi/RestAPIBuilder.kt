package com.assignment.network.networkapi

import com.assignment.network.trust.Certificate.applyCertificateIfNeeded
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RestAPIBuilder {

    private val client: OkHttpClient by lazy {
        val clientBuilder = OkHttpClient.Builder().applyCertificateIfNeeded("")
        with(clientBuilder) {
            connectionPool(RestAPIContract.provideConnectPool())
            if (RestAPIContract.isDebugMode()) {
                // only log in debug mode
                addInterceptor(RestAPIContract.provideLogingInterceptor())
            }
            addNetworkInterceptor(RestAPIContract.provideNetWorkInterceptor())
            readTimeout(
                RestAPIContract.provideNetworkReadTimeOutInSecs(),
                TimeUnit.SECONDS,
            )
            writeTimeout(
                RestAPIContract.provideNetworkWriteTimeOutInSecs(),
                TimeUnit.SECONDS,
            )
            connectTimeout(
                RestAPIContract.provideNetworkConnectionTimeOutInSecs(),
                TimeUnit.SECONDS,
            )
            cache(RestAPIContract.provideCache())
            clientBuilder.build()
        }

    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("UNKNOWN_URL")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}