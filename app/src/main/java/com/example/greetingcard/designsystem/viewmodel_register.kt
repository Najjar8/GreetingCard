package com.example.greetingcard.designsystem

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.greetingcard.data.AppDatabase
import com.example.greetingcard.data.UserEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


//-----------------------Register view model code-------------------------
sealed class RegisterResult {
    object Idle : RegisterResult()
    object Success : RegisterResult()
    data class Error(val message: String) : RegisterResult()
}

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    private val userDao = AppDatabase.getInstance(application).userDao()

    var firstName by mutableStateOf("")
    var lastName  by mutableStateOf("")
    var email     by mutableStateOf("")
    var password  by mutableStateOf("")

    private val _registerResult = MutableStateFlow<RegisterResult>(RegisterResult.Idle)
    val registerResult: StateFlow<RegisterResult> = _registerResult

    fun onFirstNameChange(v: String) { firstName = v }
    fun onLastNameChange(v: String)  { lastName  = v }
    fun onEmailChange(v: String)     { email     = v }
    fun onPasswordChange(v: String)  { password  = v }

    fun register() {
        if (firstName.isBlank() || lastName.isBlank() || email.isBlank() || password.isBlank()) {
            _registerResult.value = RegisterResult.Error("All fields are required.")
            return
        }
        viewModelScope.launch {
            val existing = userDao.getUserByEmail(email)
            if (existing != null) {
                _registerResult.value = RegisterResult.Error("Email already registered.")
            } else {
                userDao.insertUser(
                    UserEntity(
                        firstName = firstName,
                        lastName  = lastName,
                        email     = email,
                        password  = password
                    )
                )
                _registerResult.value = RegisterResult.Success
            }
        }
    }

    fun resetResult() { _registerResult.value = RegisterResult.Idle }
}