package com.example.weatherapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FavoriteLocation::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}