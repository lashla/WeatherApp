package com.example.weatherapp.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/current")
    suspend fun getCityTemp(
        @Query("query") name: String,
        @Query("access_key") access_key: String
    ): Response<WeatherResponse>
    companion object {
        var retrofitService: WeatherApi? = null
        fun getInstance() : WeatherApi {
            val okHttpClient by lazy {
                OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor()).build()
            }
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("http://api.weatherstack.com/") //         android:usesCleartextTraffic="true"
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
                retrofitService = retrofit.create(WeatherApi::class.java)
            }
            return retrofitService!!
        }

    }
}

