package com.assignment.network.networkapi

import com.assignment.network.intercep.NetworkInterceptor
import com.assignment.shared.BuildConfig
import com.assignment.shared.SharedModuleMigration
import okhttp3.Cache
import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File

// provide for RestAPIBuilder
object RestAPIContract {

    // cache folder
    private const val cacheDirName = "NabResponseCache"

    // 100MB for cache
    private const val DEFAULT_CACHE_SIZE = 100 * 1024 * 1024L

    private const val NETWORK_CONNECTION_TIME_OUT_DEFAULT_IN_SECS = 20L
    private const val NETWORK_READ_TIME_OUT_DEFAULT_IN_SECS = NETWORK_CONNECTION_TIME_OUT_DEFAULT_IN_SECS
    private const val NETWORK_WRITE_TIME_OUT_DEFAULT_IN_SECS = NETWORK_CONNECTION_TIME_OUT_DEFAULT_IN_SECS

    private val cacheDir: File by lazy {
        File(SharedModuleMigration.application.cacheDir, cacheDirName).also {
            if (it.exists().not()) {
                it.mkdir()
            }
        }
    }

    private val loggingInterceptor: Interceptor by lazy {
        HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.HEADERS
        }
    }

    fun provideCache(): Cache = Cache(cacheDir, DEFAULT_CACHE_SIZE)

    fun provideNetWorkInterceptor(): NetworkInterceptor = NetworkInterceptor()

    fun provideLogingInterceptor(): Interceptor = loggingInterceptor

    fun provideConnectPool(): ConnectionPool = ConnectionPool()

    fun isDebugMode(): Boolean = BuildConfig.DEBUG

    fun provideNetworkReadTimeOutInSecs(): Long = NETWORK_READ_TIME_OUT_DEFAULT_IN_SECS

    fun provideNetworkWriteTimeOutInSecs(): Long = NETWORK_WRITE_TIME_OUT_DEFAULT_IN_SECS

    fun provideNetworkConnectionTimeOutInSecs(): Long = NETWORK_CONNECTION_TIME_OUT_DEFAULT_IN_SECS


}