package com.example.weatherapp.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("/current")
    fun getCityTemp(
        @Query("name") name: String,
        @Query("accessKey") access_key: String
    ): Call<WeatherResponse>

}