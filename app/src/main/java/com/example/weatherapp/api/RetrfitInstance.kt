package com.example.weatherapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrfitInstance {
    private val api: WeatherApi by lazy{
        Retrofit.Builder()
            .baseUrl("http://api.weatherstack.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
}