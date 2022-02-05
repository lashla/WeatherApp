package com.example.weatherapp.api

import com.google.gson.annotations.SerializedName

class WeatherResponse {
    @SerializedName("temp")
    var temp: Int = 0
    @SerializedName("name")
    var name: String? = null
}