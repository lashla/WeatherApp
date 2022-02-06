package com.example.weatherapp.api

import com.google.gson.annotations.SerializedName

class WeatherResponse {
    @SerializedName("temperature")
    var temperature: Int = 0
    @SerializedName("name")
    var name: String? = null
    @SerializedName("weather_icons")
    var weather_icons = ArrayList<String>()
}