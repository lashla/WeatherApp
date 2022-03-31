package com.example.weatherapp.api

import com.google.gson.annotations.SerializedName

class WeatherResponse {
    val location: Location? = null
    val current: Current? = null
}

class Current{
    val temperature: Int = 0
    @SerializedName("weather_icons")
    var weatherIcons: List<String> = listOf<String>()
    val visibility: Int = 0
    @SerializedName("wind-speed")
    val windSpeed: Int = 0
    val pressure: Int = 0
    val humidity: Int = 0
    @SerializedName("weather_descriptions")
    val weatherDescriptions: List<String> = listOf<String>()
}

class Location{
    val name: String? = null
}
