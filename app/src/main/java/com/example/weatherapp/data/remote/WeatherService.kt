package com.example.weatherapp.data.remote

import com.example.weatherapp.data.models.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("data/2.5/forecats")
    suspend fun getWeather(
        @Query("q") cityName: String,
        @Query("appid") APIKEY: String,
        @Query("units") units: String = "imperial"
    ) : Response<WeatherResponse>

}