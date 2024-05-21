package com.example.journey.viewModels

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.journey.MainActivity
import com.example.journey.data.models.JourneyResponse
import com.example.journey.data.models.NewJourneyRequest
import com.example.journey.data.repository.JourneyRepository
import kotlinx.coroutines.launch

class JourneyViewModel : ViewModel() {
    private val _journeyRepository = JourneyRepository()
    private var _journeySelected: JourneyResponse? = null
    private var _journeysList: List<JourneyResponse>? = null

    private var _viewModelIsReady by mutableStateOf(true)

    fun setSelectedJourney(journey: JourneyResponse) {
        _journeySelected = journey
    }

    fun getSelectedJourney(): JourneyResponse? {
        return _journeySelected
    }

    fun listJourneys(): List<JourneyResponse>? {
        return _journeysList
    }

    fun loadJourneys() {
        _viewModelIsReady = false
        viewModelScope.launch {
            _journeysList = _journeyRepository.listJourneys()
            _viewModelIsReady = true
        }
    }

    fun createJourney(context: MainActivity, newJourney: NewJourneyRequest, onCreateConfirm: () -> Unit) {
        viewModelScope.launch {
            val journeyIsCreated = _journeyRepository.createJourney(newJourney)
            if (journeyIsCreated) {
                loadJourneys()
                onCreateConfirm.invoke()
            } else {
                Toast.makeText(
                    context,
                    "Não foi possível criar a jornada",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun isReady(): Boolean {
        return _viewModelIsReady
    }
}