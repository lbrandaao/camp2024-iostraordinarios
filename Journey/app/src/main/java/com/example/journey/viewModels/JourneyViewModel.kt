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
import com.example.journey.data.models.Superpower
import com.example.journey.data.models.Tag
import com.example.journey.data.models.UserResponse
import com.example.journey.data.repository.JourneyRepository
import kotlinx.coroutines.launch

/*private val lista = listOf(
    JourneyResponse(
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
            journeysCompleted = 0
        ),
        nuts = 100,
        id = 1
    ),
    JourneyResponse(
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
            journeysCompleted = 0
        ),
        nuts = 100,
        id = 2
    ),
    JourneyResponse(
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
            journeysCompleted = 0
        ),
        nuts = 100,
        id = 3
    ),
    JourneyResponse(
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
            journeysCompleted = 0
        ),
        nuts = 100,
        id = 4
    )
)*/

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