package com.example.weatherapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.adapters.WeatherAdapter
import com.example.weatherapp.data.remote.WeatherManager
import com.example.weatherapp.databinding.FragmentCityBinding
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.viewModels.WeatherViewModel
import com.example.weatherapp.viewModels.WeatherViewModelFactory


class WeatherFragment : Fragment() {

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!
    private lateinit var weatherAdapter: WeatherAdapter

    private lateinit var weatherViewModel: WeatherViewModel //by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        weatherViewModel = ViewModelProvider(
            this,
            WeatherViewModelFactory(WeatherRepository(WeatherManager()))
        ).get(WeatherViewModel::class.java)

        weatherViewModel.weatherData.observe(viewLifecycleOwner, Observer {
            weatherAdapter.differ.submitList(it.data?.list)
        })
    }

    private fun setupRecyclerView() = binding.rvWeatherDays.apply {
        weatherAdapter = WeatherAdapter()
        adapter = weatherAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }
}

