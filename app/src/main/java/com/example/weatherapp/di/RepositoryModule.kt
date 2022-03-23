package com.example.weatherapp.di

import com.example.weatherapp.ViewModelComponents.MainRepository
import com.example.weatherapp.api.WeatherApi
import com.example.weatherapp.api.WeatherResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule  {

    @Singleton
    @Provides
    fun providesRepository(retrofit: WeatherApi): MainRepository{
        return MainRepository(retrofit)
    }

}