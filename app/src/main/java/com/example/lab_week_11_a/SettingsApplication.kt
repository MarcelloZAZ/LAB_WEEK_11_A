package com.example.lab_week_11_a

import android.app.Application

class SettingsApplication : Application() {
    // Inisialisasi SettingsStore di sini
    lateinit var settingsStore: SettingsStore

    override fun onCreate() {
        super.onCreate()
        settingsStore = SettingsStore(this)
    }
}