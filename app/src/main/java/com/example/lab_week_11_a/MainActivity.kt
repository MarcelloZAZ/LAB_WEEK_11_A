package com.example.lab_week_11_a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Mendapatkan instance SettingsStore dari Application Class
        val settingsStore = (application as SettingsApplication).settingsStore

        // 2. Membuat ViewModel menggunakan Factory
        val settingsViewModel = ViewModelProvider(this, object :
            ViewModelProvider.Factory {
            // override fun create(modelClass: Class<T>, extras: CreationExtras): T { // Gunakan ini jika Android Studio Anda menyarankan
            //     return SettingsViewModel(settingsStore) as T
            // }

            // Factory untuk versi Android/Lifecycle lama
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return SettingsViewModel(settingsStore) as T
            }
        })[SettingsViewModel::class.java]

        // 3. Mengamati (Observe) LiveData dari ViewModel
        settingsViewModel.textLiveData.observe(this) { savedText ->
            // Memperbarui TextView saat data berubah
            findViewById<TextView>(R.id.activity_main_text_view).text = "Tersimpan: $savedText"
        }

        // 4. Mengatur OnClickListener untuk Tombol
        findViewById<Button>(R.id.activity_main_button).setOnClickListener {
            val inputText = findViewById<EditText>(R.id.activity_main_edit_text).text.toString()
            // Memanggil fungsi simpan di ViewModel
            settingsViewModel.saveText(inputText)
        }
    }
}