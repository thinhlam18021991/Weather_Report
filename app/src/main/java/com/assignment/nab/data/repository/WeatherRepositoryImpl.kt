package com.assignment.nab.data.repository

import com.assignment.model.Result
import com.assignment.model.weather.WeatherMapper
import com.assignment.model.weather.WeatherModel
import com.assignment.nab.data.utils.Constant
import com.assignment.nab.domain.repository.WeatherRepository
import com.assignment.nab.presentation.di.IoDispatcher
import com.assignment.network.apiservice.RestApiService
import com.assignment.security.SecurityHelper
import com.assignment.security.SecurityNab
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val restApiService: RestApiService,
    private val securityNab: SecurityNab,
    @IoDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) :
    WeatherRepository {
    override suspend fun getWeather(city: String): Result<List<WeatherModel>> {
        return withContext(dispatcher) {
            try {
                val result = restApiService.getWeather(
                    city, Constant.NUMBER_O_FDAY, securityNab.appId(), Constant.UNIT
                )
                if (result.code != Constant.SUCCESS_CODE) {
                    Result.Error(result.message.orEmpty())
                } else {
                    Result.Success(result.forecastList?.map {
                        WeatherMapper.toModel(it)
                    } ?: emptyList())
                }
            } catch (ex: Exception) {
                Result.Error(ex.toString())
            }
        }
    }
}