package com.example.weatherapp.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore by preferencesDataStore(name = "settings")

class SettingsDataStore @Inject constructor(@ApplicationContext private val context: Context){

    companion object {
        private val UNIT_KEY = stringPreferencesKey("unit_preference")
    }

    suspend fun saveUnit(unit: String) {
        context.dataStore.edit { settings ->
            settings[UNIT_KEY] = unit
        }
    }

    val unitFlow: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[UNIT_KEY] ?: "metric"
        }
}