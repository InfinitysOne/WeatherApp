package com.example.weatherapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherapp.ui.WeatherScreen
import com.example.weatherapp.ui.FavoritesScreen

sealed class Screen(val route: String) {
    object Weather : Screen("weather")
    object Favorites : Screen("favorites")
}

@Composable
fun WeatherNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Weather.route
    ) {
        composable(route = Screen.Weather.route) {
            WeatherScreen(
                onFavoritesClick = {
                    navController.navigate(Screen.Favorites.route)
                }
            )
        }
        composable(route = Screen.Favorites.route) {
            FavoritesScreen(onLocationSelected = { location ->
                navController.popBackStack()
            },
            onBackClick = {
                navController.popBackStack()
            })
        }
    }
}