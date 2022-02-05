package com.example.weatherapp.api


import com.example.weatherapp.model.Current
import com.example.weatherapp.model.Location
import com.example.weatherapp.model.Post
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("/current")
    fun getPost(@Query("2486c00d678c12f26979dcefa4344b2f") key: String): Callback<com.example.weatherapp.model.Request>

}