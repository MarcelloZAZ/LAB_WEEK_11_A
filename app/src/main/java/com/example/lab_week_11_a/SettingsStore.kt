package com.example.lab_week_11_a

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Delegated property untuk membuat DataStore
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settingsStore")

class SettingsStore (private val context: Context) {
    // Flow of String yang akan memancarkan data setiap kali KEY_TEXT berubah
    val text: Flow<String> = context.dataStore.data
        .map { preferences ->
            // Mengambil data, jika kosong, kembalikan string kosong
            preferences[KEY_TEXT] ?: ""
        }

    // Fungsi suspend untuk menyimpan data secara asinkron
    suspend fun saveText(text: String) {
        context.dataStore.edit { preferences ->
            preferences[KEY_TEXT] = text
        }
    }

    // Objek pendamping untuk mendefinisikan key
    companion object {
        val KEY_TEXT = stringPreferencesKey("key_text_storage")
    }
}