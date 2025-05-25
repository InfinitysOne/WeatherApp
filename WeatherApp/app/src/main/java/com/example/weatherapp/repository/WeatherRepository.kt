package com.example.weatherapp.repository

import com.example.weatherapp.data.api.WeatherService
import com.example.weatherapp.data.db.FavoriteDao
import com.example.weatherapp.data.db.FavoriteLocation
import com.example.weatherapp.data.model.WeatherResponse
import kotlinx.coroutines.flow.Flow


class WeatherRepository(private val api: WeatherService, private val dao: FavoriteDao) {
    private val apiKey = "d3208106feb307788bed5ae6254651d4"

    suspend fun getCurrentWeather(lat: Double, lon: Double, units: String = "metric"): WeatherResponse {
        return api.getCurrentWeather(lat, lon, apiKey, units)
    }

    fun getFavoriteLocations(): Flow<List<FavoriteLocation>> = dao.getAllFavorites()

    suspend fun addFavorite(location: FavoriteLocation) = dao.insert(location)

    suspend fun removeFavorite(location: FavoriteLocation) = dao.delete(location)
}