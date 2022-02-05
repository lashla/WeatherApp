package com.example.weatherapp.api


import com.example.weatherapp.model.Current
import okhttp3.Response
import retrofit2.http.GET

interface WeatherApi {
    fun getPost(): Response<Current>
}