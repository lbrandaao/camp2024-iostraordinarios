package com.example.journey.data.repository

import com.example.journey.data.models.Login
import com.example.journey.data.models.NewUserRequest
import com.example.journey.data.models.UserResponse
import com.example.journey.data.remote.RetrofitInstance
import com.example.journey.data.remote.TokenManager

class UserRepository {
    private val _userService = RetrofitInstance.userService
    suspend fun createUser(newUser: NewUserRequest): Boolean {
        var userCreated: UserResponse? = null
        val response = _userService.createUser(newUser)

        if (response.isSuccessful) {
            userCreated = response.body()
        }

        return userCreated!=null
    }

    suspend fun authenticateUser(email: String, password: String): Boolean {
        var token: String? = null
        val response = _userService.login(
            Login(email, password)
        )

        if (response.isSuccessful) {
            response.body()?.let {
                TokenManager.setToken(it.access_token)
                token = it.access_token
            }
        }

        return token!=null
    }

    suspend fun getAuthenticatedUser(): UserResponse? {
        val requestToken = "Bearer " + TokenManager.getToken()
        var authenticatedUser: UserResponse? = null
        val response = _userService.auth(requestToken)

        if (response.isSuccessful) {
            authenticatedUser = response.body()
        }

        return authenticatedUser
    }
}