package com.example.weatherapp.api

import android.content.ContentValues
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ViewModel(query: String) {
    private var cityArrayList = ArrayList<String>()
    private var currentTemperature: Int = 0
    fun someRetrofit(query: String,){
        val retrofit = Retrofit.Builder()
            .baseUrl("http://api.weatherstack.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(WeatherApi::class.java)

        val call = service.getCityTemp(query, "2486c00d678c12f26979dcefa4344b2f")
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>){
                if (response.code() == 300) {
                    cityArrayList.add(response.body()!!.name!!)
                    currentTemperature = response.body()!!.temperature
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "${t.message} " )
            }
        })
    }
}