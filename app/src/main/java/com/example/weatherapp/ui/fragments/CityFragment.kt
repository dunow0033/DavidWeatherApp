package com.example.weatherapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.data.remote.WeatherManager
import com.example.weatherapp.databinding.FragmentCityBinding
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.utils.Constants.Companion.API_KEY
import com.example.weatherapp.viewModels.WeatherViewModel
import com.example.weatherapp.viewModels.WeatherViewModelFactory


class CityFragment : Fragment() {
    private var _binding: FragmentCityBinding? = null
    private val binding get() = _binding!!

    val weatherViewModel: WeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCityBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextButton.setOnClickListener {
            val cityName = binding.etCity.text.toString()
            weatherViewModel.getWeather(cityName,API_KEY)
            findNavController().navigate(R.id.weatherFragment)

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}