package com.example.journey.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.journey.MainActivity
import com.example.journey.data.models.Journey
import com.example.journey.data.repository.JourneyRepository
import kotlinx.coroutines.launch

class JourneyViewModel: ViewModel() {
    private val _journeyRepository = JourneyRepository()
    private var _journeySelected: Journey? = null
    private var _journeysList: List<Journey>? = null

    private var _viewModelIsReady by mutableStateOf(true)

    fun setSelectedJourney(journey: Journey) {
        _journeySelected = journey
    }

    fun getSelectedJourney(): Journey? {
        return _journeySelected
    }
    fun listJourneys(): List<Journey>? {
        return _journeysList
    }
    fun loadJourneys() {
        _viewModelIsReady = false
        viewModelScope.launch {
            _journeysList = _journeyRepository.listJourneys()
            _viewModelIsReady = true
        }
    }

    fun createJourney(context: MainActivity, journey: Journey, onCreateConfirm: () -> Unit) {
        viewModelScope.launch {
            val journeyIsCreated = _journeyRepository.createJourney(journey)
            if (journeyIsCreated) {
                loadJourneys()
                onCreateConfirm.invoke()
            }
        }
    }

    fun isReady(): Boolean {
        return _viewModelIsReady
    }
}