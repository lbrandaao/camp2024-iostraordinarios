package com.example.journey.data.repository

import com.example.journey.data.models.NewUserRequest
import com.example.journey.data.remote.services.UserService

class UserRepository {
    private val _userService = UserService()

    fun login(email: String, password: String): Boolean {
        _userService.login(email, password)
        return true
    }

    fun createUser(newUser: NewUserRequest): Boolean {
        return true
    }
}