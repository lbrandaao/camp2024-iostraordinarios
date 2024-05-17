package com.example.journey.viewModels

import androidx.lifecycle.ViewModel
import com.example.journey.data.models.Journey
import com.example.journey.data.repository.JourneyRepository

class JourneyViewModel: ViewModel() {
    private val _journeyRepository = JourneyRepository()
    private var _journeySelected: Journey? = null

    fun setSelectedJourney(journey: Journey) {
        _journeySelected = journey
    }

    fun getSelectedJourney(): Journey? {
        return _journeySelected
    }
    fun listJourneys(): List<Journey> {
        return _journeyRepository.listJourneys()
    }

    fun createJourney(journey: Journey) {
        _journeyRepository.createJourney(journey)
    }
}