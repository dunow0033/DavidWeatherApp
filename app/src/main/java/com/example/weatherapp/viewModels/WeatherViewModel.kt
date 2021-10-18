package com.example.weatherapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.models.WeatherResponse
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

public class WeatherViewModel(val weatherRepository: WeatherRepository) : ViewModel() {

    private var _weatherData: MutableLiveData<Resource<WeatherResponse>> = MutableLiveData()
    val weatherData: LiveData<Resource<WeatherResponse>> get() = _weatherData


    fun getWeather(cityName: String, APIKEY: String) =
        viewModelScope.launch(Dispatchers.IO) {
            _weatherData.postValue(Resource.Loading())
            val response = weatherRepository.getWeather(cityName, APIKEY).collect {
                _weatherData.postValue(it)
            }
        }
}