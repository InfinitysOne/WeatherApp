package com.example.weatherapp.ui.screens

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageManager
import android.util.Log
import com.example.weatherapp.ui.components.ActionBar
import com.example.weatherapp.ui.components.DailyForecast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.ui.theme.ColorBackground
import com.example.weatherapp.ui.viewmodel.WeatherViewModel
import androidx.compose.runtime.getValue


fun Context.findActivity(): Activity = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> throw IllegalStateException("Context is not an Activity")
}

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel(),
    onFavoritesClick: () -> Unit
) {
    val context = LocalContext.current
    val weather by viewModel.weather.collectAsState()
    val unit by viewModel.unit.collectAsState()
    val favorites by viewModel.favorites.collectAsState()
    val isFavorited = favorites.any { it.name == weather?.name }

    // TEMP location hardcoded for now (Rome)
    LaunchedEffect(Unit) {
        val permission = Manifest.permission.ACCESS_FINE_LOCATION
        val permissionResult = ContextCompat.checkSelfPermission(context, permission)

        if (permissionResult == PackageManager.PERMISSION_GRANTED) {
            viewModel.fetchWeatherByCurrentLocation(context)
        } else {
            ActivityCompat.requestPermissions(
                context.findActivity(),
                arrayOf(permission),
                1001
            )
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = ColorBackground
    ) { paddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddings)
                .padding(horizontal = 24.dp, vertical = 15.dp)
        ) {

            ActionBar(
                location = weather?.name ?: "...",
                onFavoritesClick = onFavoritesClick,
                isFavorited = isFavorited,
                onToggleUnit = {viewModel.toggleUnit()},
                currentUnit = unit
            )
            Spacer(modifier = Modifier.height(50.dp))
            Log.d("ComposeDebug", "Rendering forecast for: ${weather?.name}, temp: ${weather?.main?.temp}")
            DailyForecast(
                degree = weather?.main?.temp?.toInt()?.toString() ?: "--",
                description = weather?.weather?.firstOrNull()?.description?.replaceFirstChar { it.uppercase() }
                    ?: "Loading...",
                wind = "${weather?.wind?.speed ?: "--"} m/s"
            )
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}
