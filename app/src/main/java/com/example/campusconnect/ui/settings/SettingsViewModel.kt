package com.example.campusconnect.ui.settings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SettingsViewModel : ViewModel() {
    private val _isDarkMode = MutableStateFlow<Boolean?>(null) // null means follow system
    val isDarkMode = _isDarkMode.asStateFlow()

    private val _isOfflineMode = MutableStateFlow(false)
    val isOfflineMode = _isOfflineMode.asStateFlow()

    private val _downloadOverWifiOnly = MutableStateFlow(true)
    val downloadOverWifiOnly = _downloadOverWifiOnly.asStateFlow()

    fun setDarkMode(enabled: Boolean?) {
        _isDarkMode.value = enabled
    }

    fun setOfflineMode(enabled: Boolean) {
        _isOfflineMode.value = enabled
    }

    fun setDownloadOverWifiOnly(enabled: Boolean) {
        _downloadOverWifiOnly.value = enabled
    }
}
