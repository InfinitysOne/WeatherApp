package com.example.weatherapp.data.model

data class WeatherResponse (
    val cityName: String,
    val main: Main,
    val weather: List<Weather>,
    val wind: Wind
)

data class Main(
    val temp: Double,
    val humidity: Int
)

data class Weather(
    val icon: String,
    val description: String
)

data class Wind(
    val speed: Double
)
