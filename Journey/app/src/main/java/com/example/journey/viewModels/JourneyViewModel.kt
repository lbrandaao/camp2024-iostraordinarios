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

    fun joinJourney(
        context: MainActivity,
        journeyId: Int,
        onJoinConfirm: () -> Unit
    ) {
        _viewModelIsReady = false
        viewModelScope.launch {
            val joinned = _journeyRepository.joinJourney(journeyId)
            if (joinned) {
                onJoinConfirm.invoke()
            } else {
                Toast.makeText(
                    context,
                    "Não foi possível se juntar à jornada.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            _viewModelIsReady = true
        }
    }

    fun completeJourney(
        context: MainActivity,
        journeyId: Int,
        onCompleteConfirm: () -> Unit
    ) {
        _viewModelIsReady = false
        viewModelScope.launch {
            val completed = _journeyRepository.completeJourney(journeyId)
            if (completed) {
                onCompleteConfirm.invoke()
            } else {
                Toast.makeText(
                    context,
                    "Não foi possível concluir a jornada.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            _viewModelIsReady = true
        }
    }

    fun isReady(): Boolean {
        return _viewModelIsReady
    }
}