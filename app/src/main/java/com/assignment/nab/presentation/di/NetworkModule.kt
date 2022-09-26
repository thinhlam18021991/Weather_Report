package com.assignment.nab.presentation.di

import com.assignment.nab.BuildConfig
import com.assignment.network.apiservice.RestApiService
import com.assignment.network.networkapi.RestAPIBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
// dependencies binding to the application
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    fun provideRestAPIService(): RestApiService {
        return RestAPIBuilder.restAPIService(BuildConfig.END_POINT)
    }

}