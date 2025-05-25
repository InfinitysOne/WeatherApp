package com.example.weatherapp.di

import com.example.weatherapp.data.api.WeatherService
import com.example.weatherapp.data.db.FavoriteDao
import com.example.weatherapp.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(
        weatherService: WeatherService,
        favoriteDao: FavoriteDao
    ): WeatherRepository {
        return WeatherRepository(weatherService, favoriteDao)
    }
}