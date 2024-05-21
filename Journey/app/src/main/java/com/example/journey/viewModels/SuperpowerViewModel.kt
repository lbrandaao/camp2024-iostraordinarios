package com.example.journey.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.journey.data.models.Superpower
import com.example.journey.data.models.SuperpowerRankingResponse
import com.example.journey.data.repository.SuperpowerRepository
import kotlinx.coroutines.launch

private val rankingSuperpowersList = listOf(
    SuperpowerRankingResponse(
        Superpower(1, "O impenetrável escudo do cuidado"),
        24000
    ),
    SuperpowerRankingResponse(
        Superpower(4, "A varinha mágica da transformação"),
        22000
    ),
    SuperpowerRankingResponse(
        Superpower(3, "O poder infinito da mente"),
        21000
    ),
    SuperpowerRankingResponse(
        Superpower(2, "O incrível cristal do extraordinário"),
        20500
    ),
    SuperpowerRankingResponse(
        Superpower(6, "As maravilhosas asas para inovar"),
        20000
    ),
    SuperpowerRankingResponse(
        Superpower(7, "A fabulosa flecha da agilidade"),
        19900
    ),
    SuperpowerRankingResponse(
        Superpower(5, "O indestrutível laço da evolução"),
        19700
    )
)

class SuperpowerViewModel: ViewModel() {
    private val _superpowerRepository = SuperpowerRepository()
    private var _superpowersList: List<Superpower>? = null

    private var _rankingSuperpowersList: List<SuperpowerRankingResponse>? = rankingSuperpowersList

    private var _viewModelIsReady by mutableStateOf(true)
    fun listSuperpowers(): List<Superpower>? {
        return _superpowersList
    }

    fun loadSuperpowers() {
        _viewModelIsReady = false
        viewModelScope.launch {
            _superpowersList = _superpowerRepository.listSuperpowers()
            _viewModelIsReady = true
        }
    }

    fun listSuperpowersRankingList(): List<SuperpowerRankingResponse>? {
        return _rankingSuperpowersList
    }

    fun loadSuperpowersRankingList() {
        /*TODO*/
    }

    fun isReady(): Boolean {
        return _viewModelIsReady
    }
}