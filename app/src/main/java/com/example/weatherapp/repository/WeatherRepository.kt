package com.example.weatherapp.repository

import android.util.Log
import com.example.weatherapp.data.remote.WeatherManager
import com.example.weatherapp.utils.Resource
import kotlinx.coroutines.flow.flow

class WeatherRepository(private val weatherManager: WeatherManager) {

    fun getWeather(cityName: String, APIKEY: String) = flow {

        val resource = try {
            val response = weatherManager.getWeather(cityName, APIKEY)
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error(response.errorBody().toString())
            }
        } catch (ex: Exception) {
            Log.d(TAG, ex.toString())
            Resource.Error(ex.toString())
        }
        emit(resource)
    }


    companion object {
        private val TAG = WeatherRepository::class.java.name
    }

}














