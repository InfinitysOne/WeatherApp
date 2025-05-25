package com.example.weatherapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_locations")
data class FavoriteLocation (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val cityName: String,
    val lat: Double,
    val lon: Double
)