package com.example.lab_week_11_a

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SettingsViewModel (private val settingsStore: SettingsStore):
    ViewModel() {

    // LiveData yang akan diamati oleh Activity/Fragment
    private val _textLiveData = MutableLiveData<String>()
    val textLiveData: LiveData<String> = _textLiveData

    init {
        // Meluncurkan coroutine untuk mengumpulkan data dari DataStore (Flow)
        viewModelScope.launch {
            settingsStore.text.collect {
                // Memperbarui LiveData setiap kali Flow memancarkan nilai baru
                _textLiveData.value = it
            }
        }
    }

    // Memanggil fungsi saveText di SettingsStore di dalam coroutine
    fun saveText(text: String) {
        viewModelScope.launch {
            settingsStore.saveText(text)
        }
    }
}