package com.example.weatherapp.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface OpenWeatherApi {

    @GET("/forecast/daily")
    suspend fun getCityTemp(
        @Query("q") cityName: String,
        @Query("cnt") count: Int,
        @Query("appid") appId: String
    ): Response<WeatherResponse>

}

