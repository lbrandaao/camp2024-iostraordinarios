package com.example.journey.data.repository

import com.example.journey.data.models.Journey
import com.example.journey.data.remote.services.JourneyService

class JourneyRepository {
    private val _journeyService = JourneyService()

    fun listJourneys(): List<Journey> {
        return _journeyService.getJourneysList()
    }

    fun createJourney(journey: Journey) {
        _journeyService.createJourney(journey)
    }
}