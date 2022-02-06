package com.example.weatherapp.api

import com.google.gson.annotations.SerializedName

class WeatherResponse {
    val location: Location? = null
    val current: Current? = null
}

class Current{
    val temperature: Int? = 0
    @SerializedName("weather_icons")
    var weatherIcons: List<String>? = listOf<String>()
}

class Location{
    val name: String? = null
}
