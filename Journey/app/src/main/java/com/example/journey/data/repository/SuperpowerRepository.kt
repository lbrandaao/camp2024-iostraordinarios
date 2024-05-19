package com.example.journey.data.repository

import com.example.journey.data.models.Superpower
import com.example.journey.data.remote.RetrofitInstance
import com.example.journey.data.remote.TokenManager

class SuperpowerRepository {
    private val _superpowerService = RetrofitInstance.superpowerService

    suspend fun listSuperpowers(): List<Superpower>? {
        val requestToken = "Bearer " + TokenManager.getToken()
        val response = _superpowerService.getAllSuperpowers(requestToken)

        return response.body()
    }

    suspend fun getSuperpower(id: Int): Superpower? {
        val requestToken = "Bearer " + TokenManager.getToken()
        val response = _superpowerService.getSuperpower(requestToken, id)

        return response.body()
    }
}