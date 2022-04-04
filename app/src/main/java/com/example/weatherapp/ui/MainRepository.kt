package com.example.weatherapp.ui

import com.example.weatherapp.api.OpenWeatherApi
import com.example.weatherapp.api.WeatherResponse
import retrofit2.Response
import javax.inject.Singleton

@Singleton
interface RepositoryInterface{
    suspend fun getTempInfo(cityName: String, retrofitService: OpenWeatherApi) = retrofitService.getCityTemp(cityName,
        6,"metric", "6941119512c9b9a65ba6b2583362f475")
}

@Singleton
class MainRepository : RepositoryInterface {
    override suspend fun getTempInfo(
        cityName: String,
        retrofitService: OpenWeatherApi,
    ): Response<WeatherResponse> {
        return super.getTempInfo(cityName, retrofitService)
    }
}