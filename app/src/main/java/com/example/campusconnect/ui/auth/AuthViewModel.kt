package com.example.campusconnect.ui.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.campusconnect.data.AppDatabase
import com.example.campusconnect.data.StudentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: StudentRepository
    
    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState = _authState.asStateFlow()

    init {
        val database = AppDatabase.getDatabase(application)
        repository = StudentRepository(database.studentDao())
    }

    fun login(email: String, passwordHash: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            if (email.isBlank() || passwordHash.isBlank()) {
                _authState.value = AuthState.Error("Please fill in all fields")
                return@launch
            }
            val success = repository.authenticateOffline(email, passwordHash)
            if (success) {
                _authState.value = AuthState.Success(email)
            } else {
                _authState.value = AuthState.Error("Invalid email or password")
            }
        }
    }

    fun register(name: String, email: String, passwordHash: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            if (name.isBlank() || email.isBlank() || passwordHash.isBlank()) {
                _authState.value = AuthState.Error("Please fill in all fields")
                return@launch
            }
            val success = repository.registerStudentLocal(name, email, passwordHash)
            if (success) {
                _authState.value = AuthState.Success(email)
            } else {
                _authState.value = AuthState.Error("Email already registered")
            }
        }
    }

    fun resetState() {
        _authState.value = AuthState.Idle
    }
}

sealed interface AuthState {
    object Idle : AuthState
    object Loading : AuthState
    data class Success(val email: String) : AuthState
    data class Error(val message: String) : AuthState
}
