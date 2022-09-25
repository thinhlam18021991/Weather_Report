package com.assignment.network.annotations

import java.util.concurrent.TimeUnit


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@MustBeDocumented
annotation class ResponseCacheControl(
    val maxAge: Int = DEFAULT_CACHE_MAX_AGE_IN_SECS,
    val maxStale: Int = DEFAULT_CACHE_MAX_STALE_IN_SECS,
    val timeUnit: TimeUnit = TimeUnit.SECONDS,
)
// 5 min
const val DEFAULT_CACHE_MAX_AGE_IN_SECS = 60 * 5
const val DEFAULT_CACHE_MAX_STALE_IN_SECS = DEFAULT_CACHE_MAX_AGE_IN_SECS
