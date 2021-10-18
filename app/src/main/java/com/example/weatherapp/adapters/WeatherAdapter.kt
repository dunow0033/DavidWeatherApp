package com.example.weatherapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.data.models.Days
import com.example.weatherapp.databinding.ItemWeatherBinding

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    inner class WeatherViewHolder(val binding: ItemWeatherBinding) :
        RecyclerView.ViewHolder(binding.root)


    private val differCallback = object : DiffUtil.ItemCallback<Days>() {
        override fun areItemsTheSame(oldItem: Days, newItem: Days): Boolean {
            return oldItem.wind == newItem.wind
        }

        override fun areContentsTheSame(oldItem: Days, newItem: Days): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(
            ItemWeatherBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.binding.apply {
            val day = differ.currentList[position]
            tvDays.text = day.weather[position].main.toString()
            tvDayTemp.text = day.main.temp.toString()
        }
    }


}