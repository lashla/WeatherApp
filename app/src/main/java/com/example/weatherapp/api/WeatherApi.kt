package com.example.weatherapp.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    //У тебя тут были неправильные названия get параметров

    @GET("/current")
    fun getCityTemp(
        @Query("query") name: String,
        @Query("access_key") access_key: String
    ): Call<WeatherResponse>

}