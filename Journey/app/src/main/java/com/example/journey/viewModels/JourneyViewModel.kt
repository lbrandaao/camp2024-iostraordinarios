package com.example.journey.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.journey.MainActivity
import com.example.journey.data.models.Journey
import com.example.journey.data.models.Post
import com.example.journey.data.models.Superpower
import com.example.journey.data.models.Tag
import com.example.journey.data.models.UserResponse
import com.example.journey.data.repository.JourneyRepository
import kotlinx.coroutines.launch

private val lista = listOf(
    Journey(
        title = "",
        description = "Recentemente, participei de um projeto que envolveu a reestruturação de " +
                "alguns dos nosso processos e, utilizando princípios de inovação, propusemos uma " +
                "abordagem totalmente nova, focada na personalização e na experiência do cliente.\n" +
                "Apresentamos um plano que não apenas solucionou nosso problema, mas também " +
                "melhorou a satisfação do cliente e aumentou a eficiência operacional. " +
                "Foi gratificante ver como a busca por novas ideias e soluções trouxe resultados " +
                "tangíveis para a empresa.",
        superpowers = listOf(
            Superpower(0, "As maravilhosas asas para voar")
        ),
        tags = listOf(
            Tag(0, "Desenvolvimento pessoal"),
            Tag(0, "Surpreender"),
            Tag(0, "Inovação")
        ),
        creator = UserResponse(
            id = 0,
            fullName = "Amélia dos Santos",
            email = "",
            position = "",
            role = "",
            nuts = 0,
            superpower = Superpower(0, "As maravilhosas asas para voar"),
            tags = listOf(
                Tag(0, "Desenvolvimento pessoal"),
                Tag(0, "Surpreender"),
                Tag(0, "Inovação")
            ),
            bio = "",
            interactionsCount = 0,
            score = 0,
            missionsCompleted = 0
        ),
        nuts = 100
    ),
    Journey(
        title = "",
        description = "Recentemente, participei de um projeto que envolveu a reestruturação de " +
                "alguns dos nosso processos e, utilizando princípios de inovação, propusemos uma " +
                "abordagem totalmente nova, focada na personalização e na experiência do cliente.\n" +
                "Apresentamos um plano que não apenas solucionou nosso problema, mas também " +
                "melhorou a satisfação do cliente e aumentou a eficiência operacional. " +
                "Foi gratificante ver como a busca por novas ideias e soluções trouxe resultados " +
                "tangíveis para a empresa.",
        superpowers = listOf(
            Superpower(0, "As maravilhosas asas para voar")
        ),
        tags = listOf(
            Tag(0, "Desenvolvimento pessoal"),
            Tag(0, "Surpreender"),
            Tag(0, "Inovação")
        ),
        creator = UserResponse(
            id = 0,
            fullName = "Amélia dos Santos",
            email = "",
            position = "",
            role = "",
            nuts = 0,
            superpower = Superpower(0, "As maravilhosas asas para voar"),
            tags = listOf(
                Tag(0, "Desenvolvimento pessoal"),
                Tag(0, "Surpreender"),
                Tag(0, "Inovação")
            ),
            bio = "",
            interactionsCount = 0,
            score = 0,
            missionsCompleted = 0
        ),
        nuts = 100
    ),
    Journey(
        title = "",
        description = "Recentemente, participei de um projeto que envolveu a reestruturação de " +
                "alguns dos nosso processos e, utilizando princípios de inovação, propusemos uma " +
                "abordagem totalmente nova, focada na personalização e na experiência do cliente.\n" +
                "Apresentamos um plano que não apenas solucionou nosso problema, mas também " +
                "melhorou a satisfação do cliente e aumentou a eficiência operacional. " +
                "Foi gratificante ver como a busca por novas ideias e soluções trouxe resultados " +
                "tangíveis para a empresa.",
        superpowers = listOf(
            Superpower(0, "As maravilhosas asas para voar")
        ),
        tags = listOf(
            Tag(0, "Desenvolvimento pessoal"),
            Tag(0, "Surpreender"),
            Tag(0, "Inovação")
        ),
        creator = UserResponse(
            id = 0,
            fullName = "Amélia dos Santos",
            email = "",
            position = "",
            role = "",
            nuts = 0,
            superpower = Superpower(0, "As maravilhosas asas para voar"),
            tags = listOf(
                Tag(0, "Desenvolvimento pessoal"),
                Tag(0, "Surpreender"),
                Tag(0, "Inovação")
            ),
            bio = "",
            interactionsCount = 0,
            score = 0,
            missionsCompleted = 0
        ),
        nuts = 100
    ),
    Journey(
        title = "",
        description = "Recentemente, participei de um projeto que envolveu a reestruturação de " +
                "alguns dos nosso processos e, utilizando princípios de inovação, propusemos uma " +
                "abordagem totalmente nova, focada na personalização e na experiência do cliente.\n" +
                "Apresentamos um plano que não apenas solucionou nosso problema, mas também " +
                "melhorou a satisfação do cliente e aumentou a eficiência operacional. " +
                "Foi gratificante ver como a busca por novas ideias e soluções trouxe resultados " +
                "tangíveis para a empresa.",
        superpowers = listOf(
            Superpower(0, "As maravilhosas asas para voar")
        ),
        tags = listOf(
            Tag(0, "Desenvolvimento pessoal"),
            Tag(0, "Surpreender"),
            Tag(0, "Inovação")
        ),
        creator = UserResponse(
            id = 0,
            fullName = "Amélia dos Santos",
            email = "",
            position = "",
            role = "",
            nuts = 0,
            superpower = Superpower(0, "As maravilhosas asas para voar"),
            tags = listOf(
                Tag(0, "Desenvolvimento pessoal"),
                Tag(0, "Surpreender"),
                Tag(0, "Inovação")
            ),
            bio = "",
            interactionsCount = 0,
            score = 0,
            missionsCompleted = 0
        ),
        nuts = 100
    )
)

class JourneyViewModel : ViewModel() {
    private val _journeyRepository = JourneyRepository()
    private var _journeySelected: Journey? = null
    private var _journeysList: List<Journey>? = lista

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