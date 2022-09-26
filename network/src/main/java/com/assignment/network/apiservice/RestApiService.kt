package com.assignment.network.apiservice

import com.assignment.network.annotations.ResponseCacheControl
import com.assignment.networkmodel.WeatherRemote
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApiService {

    @GET("/data/2.5/forecast/daily")
    @ResponseCacheControl
    suspend fun getWeather(@Query("q") cityName: String,
                        @Query("cnt") numberDays: Int,
                        @Query("appid") appId: String,
                        @Query("units") units: String): WeatherRemote

}