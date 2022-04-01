package com.example.weatherapp.api

import com.google.gson.annotations.SerializedName

class WeatherResponse(
    @SerializedName("city") val city : City,
    @SerializedName("temp") val temp: Temp?,
    @SerializedName("list") val list : List,
    @SerializedName("weather")val weather: Weather
)

class City (
    val name: String? = null
)

class Temp (
    var day   : Double? = null
        )

class Weather(
    var description : String? = null,
    var icon        : String? = null
)

class List(
    var pressure  : Double?,
    var humidity  : Int?,
    var gust      : Double?,
    var speed     : Double?
)