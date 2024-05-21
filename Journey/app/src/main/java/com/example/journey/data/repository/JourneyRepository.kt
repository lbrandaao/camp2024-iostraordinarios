package com.example.journey.data.repository

import com.example.journey.data.models.JourneyResponse
import com.example.journey.data.models.NewJourneyRequest
import com.example.journey.data.remote.RetrofitInstance
import com.example.journey.data.remote.TokenManager

class JourneyRepository {
    private val _journeyService = RetrofitInstance.journeyService
    suspend fun createJourney(newJourney: NewJourneyRequest): Boolean {
        val requestToken = "Bearer " + TokenManager.getToken()
        val response = _journeyService.createJourney(
            token = requestToken,
            newJourney = newJourney
        )

        return response.isSuccessful
    }

    suspend fun listJourneys(): List<JourneyResponse>? {
        val requestToken = "Bearer " + TokenManager.getToken()
        val response = _journeyService.getAllJourneys(requestToken)

        return response.body()
    }

    suspend fun getJourney(id: Int): JourneyResponse? {
        val requestToken = "Bearer " + TokenManager.getToken()
        val response = _journeyService.getJourney(requestToken, id)

        return response.body()
    }

    suspend fun joinJourney(journeyId: Int): Boolean {
        val requestToken = "Bearer " + TokenManager.getToken()
        val response = _journeyService.joinJourney(
            token = requestToken,
            id = journeyId
        )

        return response.isSuccessful
    }

    suspend fun completeJourney(journeyId: Int): Boolean {
        val requestToken = "Bearer " + TokenManager.getToken()
        val response = _journeyService.completeJourney(
            token = requestToken,
            id = journeyId
        )

        return response.isSuccessful
    }
}