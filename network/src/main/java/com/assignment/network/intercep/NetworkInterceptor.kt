package com.assignment.network.intercep

import com.assignment.network.annotations.ResponseCacheControl
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Invocation
import java.util.concurrent.TimeUnit

class NetworkInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // Get the request from the chain.
        val originalRequest = chain.request()
        val method = originalRequest.tag(Invocation::class.java)?.method()
        val responseCacheControl = method?.getAnnotation(ResponseCacheControl::class.java)
        val originalResponse = chain.proceed(originalRequest)

        // if request != Get no need to apply cache control
        if (originalRequest.method != "GET") return originalResponse

        if (responseCacheControl != null && originalResponse.isSuccessful) {
            val maxAge: Int = TimeUnit.SECONDS.convert(
                responseCacheControl.maxAge.toLong(),
                responseCacheControl.timeUnit,
            ).toInt()
            val maxStale: Int = TimeUnit.SECONDS.convert(
                responseCacheControl.maxStale.toLong(),
                responseCacheControl.timeUnit,
            ).toInt()
            require(maxAge >= 0)
            require(maxStale >= 0)
            // Setting the max age to maxAge, which means the cache will be valid for maxAge. For example,
            // the first request will be getting response from the server,
            // and the following request made within maxAge minute of the first request
            // will be getting response from the cache
            // if the time is over and server down or disconnect network
            // based on maxStale to return the cache response
            return originalResponse.newBuilder()
                .header(
                    "Cache-Control",
                    "public, max-age=$maxAge, max-stale=$maxStale, s-max-age=$maxAge",
                )
                .removeHeader("Pragma").build()
        }
        return originalResponse
    }
}