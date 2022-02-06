package com.example.weatherapp.api

import com.google.gson.annotations.SerializedName

class WeatherResponse {
    @SerializedName("location")
    val location: Location? = null
    @SerializedName("current")
    val current: Current? = null


}

class Current{
    @SerializedName("temperature")
    val temperature: Int? = 0
    @SerializedName("weather_icons")
    var weather_icons: List<String>? = listOf<String>()
}

class Location{
    @SerializedName("name")
    val name: String? = null
}
