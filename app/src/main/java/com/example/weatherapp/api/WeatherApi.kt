package com.example.weatherapp.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface OpenWeatherApi {

    @GET("/data/2.5/forecast")
    suspend fun getCityTemp(
        @Query("q") cityName: String,
        @Query("cnt") count: Int,
        @Query("units") units: String,
        @Query("appid") appId: String
    ): Response<WeatherResponse>

}
