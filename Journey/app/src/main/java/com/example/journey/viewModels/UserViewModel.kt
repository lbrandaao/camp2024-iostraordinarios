package com.example.journey.viewModels

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.journey.MainActivity
import com.example.journey.data.models.NewUserRequest
import com.example.journey.data.models.UserResponse
import com.example.journey.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val _userRepository = UserRepository()

    private var _newUserFullName: String = ""
    private var _newUserEmail: String = ""
    private var _newUserPassword: String = ""
    private var _newUserPosition: String = ""
    private var _newUserRole: String = ""

    private var _authenticatedUser: UserResponse? = null

    private var _viewModelIsReady by mutableStateOf(true)

    fun saveNewUserData(
        fullName: String,
        email: String,
        password: String,
        position: String,
        role: String
    ) {
        _newUserFullName = fullName
        _newUserEmail = email
        _newUserPassword = password
        _newUserPosition = position
        _newUserRole = role
    }

    fun createUser(
        superpowerId: Int,
        tagsIdList: List<Int>,
        context: MainActivity,
        onRegistrationConfirm: () -> Unit
    ) {
        val newUserRequest = NewUserRequest(
            fullName = _newUserFullName,
            email = _newUserEmail,
            password = _newUserPassword,
            position = _newUserPosition,
            role = _newUserRole,
            superpower = superpowerId,
            tags = tagsIdList
        )
        viewModelScope.launch {
            val newUserIsCreated = _userRepository.createUser(newUserRequest)

            if (newUserIsCreated) {
                authenticateUser(newUserRequest.email, newUserRequest.password, context, onRegistrationConfirm)
            } else Toast.makeText(
                context,
                "Algo deu errado.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun authenticateUser(
        email: String,
        password: String,
        context: MainActivity,
        onAuthConfirm: () -> Unit
    ) {
        _viewModelIsReady = false
        viewModelScope.launch {
            val userIsAuthenticated = _userRepository.authenticateUser(email, password)
            if (userIsAuthenticated) {
                _authenticatedUser = _userRepository.getAuthenticatedUser()
                onAuthConfirm.invoke()
            } else {
                Toast.makeText(
                    context,
                    "Email ou senha inválidos.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            _viewModelIsReady = true
        }
    }

    fun setAuthenticatedUser() {
        viewModelScope.launch {
            _authenticatedUser = _userRepository.getAuthenticatedUser()
        }
    }

    fun getAuthenticatedUser(): UserResponse? {
        return _authenticatedUser
    }

    fun isReady(): Boolean {
        return _viewModelIsReady
    }
}