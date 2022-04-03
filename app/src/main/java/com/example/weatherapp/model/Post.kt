package com.example.weatherapp.model

data class Post(
    val city: City?,
    val cnt: Int?,
    val cod: String?,
    val list: List<ApiResponse>?,
    val message: Int?
)