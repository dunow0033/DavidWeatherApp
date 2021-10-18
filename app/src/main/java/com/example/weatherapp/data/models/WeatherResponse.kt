package com.example.weatherapp.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Days>,
    val message: Int
)