package com.example.weatherapp.data.remote

class WeatherManager {

    private val weatherService: WeatherService
    private val retrofit = RetrofitInstance.providesRetrofit()


    init {
        weatherService = retrofit.create(WeatherService::class.java)
    }

    suspend fun getWeather(cityName: String, APIKEY: String) =
        weatherService.getWeather(cityName, APIKEY)

}
