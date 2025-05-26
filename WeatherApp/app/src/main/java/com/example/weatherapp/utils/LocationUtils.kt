package com.example.weatherapp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException

object LocationUtils {

    @SuppressLint("MissingPermission")
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    suspend fun getCurrentLocation(context: Context): Location {
        val fusedClient: FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(context)

        return suspendCancellableCoroutine { cont ->
            fusedClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
                .addOnSuccessListener { location ->
                    if (location != null) {
                        cont.resume(location) {}
                    } else {
                        cont.resumeWithException(Exception("Location is null"))
                    }
                }
                .addOnFailureListener {
                    cont.resumeWithException(it)
                }
        }
    }
}