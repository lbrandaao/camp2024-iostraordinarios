package com.example.journey.data.remote.services

import com.example.journey.data.models.Journey
import com.example.journey.data.models.Superpower
import com.example.journey.data.models.Tag
import com.example.journey.data.models.UserResponse

class JourneyService {
    private var journeyList = mutableListOf<Journey>()
    fun getJourneysList(): List<Journey> {
        return journeyList
    }

    fun createJourney(journey: Journey) {
        journeyList += journey
    }
}