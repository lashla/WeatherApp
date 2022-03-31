package com.example.weatherapp.model

data class Post(
    val current: Current,
    val location: Location,
    val request: Request,
    val astro: Astro,
    val forecast: Forecast
)