package com.example.journey.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.journey.MainActivity
import com.example.journey.data.models.Superpower
import com.example.journey.data.repository.SuperpowerRepository
import kotlinx.coroutines.launch

class SuperpowerViewModel: ViewModel() {
    private val _superpowerRepository = SuperpowerRepository()
    private var _superpowersList: List<Superpower>? = null

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

    fun isReady(): Boolean {
        return _viewModelIsReady
    }
}