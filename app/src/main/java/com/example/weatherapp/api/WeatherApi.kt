package com.example.weatherapp.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/current")
    fun getCityTemp(
        @Query("query") name: String,
        @Query("access_key") access_key: String
    ): Call<WeatherResponse>

    }
