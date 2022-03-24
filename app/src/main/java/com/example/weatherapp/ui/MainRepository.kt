package com.example.weatherapp.ui

import com.example.weatherapp.api.WeatherApi
import com.example.weatherapp.api.WeatherResponse
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Response
import javax.inject.Singleton

@Singleton
interface RepositoryInterface{
    suspend fun getTempInfo(query: String, retrofitService: WeatherApi) = retrofitService.getCityTemp(query,
        "2486c00d678c12f26979dcefa4344b2f")
}

@Singleton
class MainRepository : RepositoryInterface {
    override suspend fun getTempInfo(
        query: String,
        retrofitService: WeatherApi,
    ): Response<WeatherResponse> {
        return super.getTempInfo(query, retrofitService)
    }
}