package com.example.weatherapp.api

import com.google.gson.annotations.SerializedName

class WeatherResponse {
    @SerializedName("temperature")
    val temperature: Int? = 0
    @SerializedName("name")
    val name: String? = null
    @SerializedName("weather_icons")
    var weather_icons: List<String>? = listOf<String>()

}

