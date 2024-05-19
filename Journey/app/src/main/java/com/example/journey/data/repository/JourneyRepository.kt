package com.example.journey.data.repository

import com.example.journey.data.models.Journey
import com.example.journey.data.remote.RetrofitInstance
import com.example.journey.data.remote.TokenManager

class JourneyRepository {
    private val _journeyService = RetrofitInstance.journeyService
    suspend fun createJourney(newJourney: Journey): Boolean {
        val requestToken = "Bearer " + TokenManager.getToken()
        val response = _journeyService.createJourney(
            token = requestToken,
            newJourney = newJourney
        )

        return response.isSuccessful
    }

    suspend fun listJourneys(): List<Journey>? {
        val requestToken = "Bearer " + TokenManager.getToken()
        val response = _journeyService.getAllJourneys(requestToken)

        return response.body()
    }

    suspend fun getJourney(id: Int): Journey? {
        val requestToken = "Bearer " + TokenManager.getToken()
        val response = _journeyService.getJourney(requestToken, id)

        return response.body()
    }
}