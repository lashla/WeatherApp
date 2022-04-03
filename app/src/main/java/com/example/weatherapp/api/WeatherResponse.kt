package com.example.weatherapp.api

import com.example.weatherapp.model.*
import com.google.gson.annotations.SerializedName

class WeatherResponse(
    @SerializedName("list") val list: List<ApiResponse>,
    @SerializedName("city") val city: City
)