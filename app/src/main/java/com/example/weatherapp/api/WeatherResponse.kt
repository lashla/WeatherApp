package com.example.weatherapp.api

import com.example.weatherapp.model.City
import com.example.weatherapp.model.List
import com.example.weatherapp.model.Temp
import com.example.weatherapp.model.Weather
import com.google.gson.annotations.SerializedName

class WeatherResponse(
    @SerializedName("city") var city : City,
    @SerializedName("temp") var temp: Temp?,
    @SerializedName("list") var list : List,
    @SerializedName("weather")var weather: Weather
)

