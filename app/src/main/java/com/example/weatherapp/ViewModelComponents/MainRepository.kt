package com.example.weatherapp.ViewModelComponents

import com.example.weatherapp.api.WeatherApi

class MainRepository(private val retrofitService: WeatherApi) {

    suspend fun getTempInfo(query: String) = retrofitService.getCityTemp(query, "2486c00d678c12f26979dcefa4344b2f")

}