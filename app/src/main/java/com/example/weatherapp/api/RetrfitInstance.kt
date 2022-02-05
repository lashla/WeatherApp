package com.example.weatherapp.api

import com.example.weatherapp.model.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

object RetrfitInstance {
    fun getData() {
        val api: WeatherApi = Retrofit.Builder()
            .baseUrl("http://api.weatherstack.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)

        val retrofitData = api.getPost("2486c00d678c12f26979dcefa4344b2f")
        retrofitData.enqueue(object: Callback<Request>{
            override fun onResponse(call: Call<Request>, response: Response<Request>) {
                val responseBody = response.body()

            }

            override fun onFailure(call: Call<Request>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}