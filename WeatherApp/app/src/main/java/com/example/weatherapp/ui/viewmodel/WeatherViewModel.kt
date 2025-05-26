package com.example.weatherapp.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.model.WeatherResponse
import com.example.weatherapp.datastore.SettingsDataStore
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.utils.LocationUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val settings: SettingsDataStore
) : ViewModel() {

    private val _weather = MutableStateFlow<WeatherResponse?>(null)
    val weather: StateFlow<WeatherResponse?> = _weather

    val unit: StateFlow<String> = settings.unitFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = "metric"
    )

    fun fetchWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                val data = repository.getCurrentWeather(lat, lon, unit.value)
                Log.d("WeatherAPI", "Response JSON → main: ${data.main}, temp: ${data.main?.temp}")
                _weather.value = data
            } catch (e: Exception) {
                Log.e("WeatherViewModel", "Error: ${e.localizedMessage}")
            }
        }
    }

    fun fetchWeatherByCurrentLocation(context: Context) {
        viewModelScope.launch {
            try {
                val location = LocationUtils.getCurrentLocation(context)
                Log.d("Location", "Got location: ${location.latitude}, ${location.longitude}")
                fetchWeather(location.latitude, location.longitude)
            } catch (e: Exception) {
                Log.e("WeatherVM", "Location error: ${e.localizedMessage}")
            }
        }
    }

}