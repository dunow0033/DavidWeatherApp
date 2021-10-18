package com.example.weatherapp.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Sys(
    val pod: String
)