package com.example.greetingcard.designsystem

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.greetingcard.data.AppDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


//------------------------Login view model code-------------------------
sealed class LoginResult {
    object Idle : LoginResult()
    object Success : LoginResult()
    data class Error(val message: String) : LoginResult()
}

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val userDao = AppDatabase.getInstance(application).userDao()

    var email    by mutableStateOf("")
    var password by mutableStateOf("")

    private val _loginResult = MutableStateFlow<LoginResult>(LoginResult.Idle)
    val loginResult: StateFlow<LoginResult> = _loginResult

    fun onEmailChange(newEmail: String)       { email    = newEmail }
    fun onPasswordChange(newPassword: String) { password = newPassword }

    fun login() {
        if (email.isBlank() || password.isBlank()) {
            _loginResult.value = LoginResult.Error("Please enter your email and password.")
            return
        }
        viewModelScope.launch {
            val user = userDao.getUserByEmail(email)
            _loginResult.value = when {
                user == null            -> LoginResult.Error("No account found with this email.")
                user.password != password -> LoginResult.Error("Incorrect password.")
                else                    -> LoginResult.Success
            }
        }
    }

    fun resetResult() { _loginResult.value = LoginResult.Idle }
}



