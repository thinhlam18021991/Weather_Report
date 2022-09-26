package com.assignment.network.networkapi

import com.assignment.network.apiservice.RestApiService
import com.assignment.network.trust.Certificate.applyCertificateIfNeeded
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicReference

object RestAPIBuilder {

    private val restAPIService = AtomicReference<RestApiService?>()

    private fun isRestAPIServiceInit(): Boolean {
        return restAPIService.get() != null
    }

    fun getRestApiService(): RestApiService {
        return restAPIService.get()!!
    }

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

    private fun build(baseURL: String) {
        Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build().also { retrofit ->
                retrofit.createRestService<RestApiService>().also {
                    this.restAPIService.set(it)
                }
            }
    }

    fun restAPIService(baseURL: String): RestApiService {
        if (isRestAPIServiceInit().not()) {
            build(baseURL)
        }
        return getRestApiService()
    }
}