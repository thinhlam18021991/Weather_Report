package com.assignment.nab.presentation.di

import com.assignment.nab.data.repository.WeatherRepositoryImpl
import com.assignment.nab.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier



@Module
// dependencies binding to the view model
@InstallIn(ViewModelComponent::class)
object WeatherListModule {


    @Provides
    @IoDispatcher
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

}

@Module
// dependencies binding to the view model
@InstallIn(ViewModelComponent::class)
abstract class WeatherListBinding {
    @Binds
    abstract fun bindWeatherListRepository(
        impl: WeatherRepositoryImpl
    ): WeatherRepository
}


@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class IoDispatcher