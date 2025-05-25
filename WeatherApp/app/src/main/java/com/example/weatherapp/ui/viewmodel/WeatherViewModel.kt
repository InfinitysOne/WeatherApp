package com.example.weatherapp.ui.viewmodel

import android.util.Log
import com.example.weatherapp.data.model.WeatherResponse
import com.example.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
) :viewModel(){

    val weather = MutableStateFlow<WeatherResponse?>(null)

    fun fetchWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                val result = repository.getCurrentWeather(lat, lon)
                weather.value = result
            } catch (e: Exception) {
                Log.e("WeatherVM", "Error: ${e.message}")
            }
        }
    }
}