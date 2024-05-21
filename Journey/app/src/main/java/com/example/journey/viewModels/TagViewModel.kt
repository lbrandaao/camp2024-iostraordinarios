package com.example.journey.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.journey.data.models.Tag
import com.example.journey.data.repository.TagRepository
import kotlinx.coroutines.launch

class TagViewModel: ViewModel() {
    private val _tagRepository = TagRepository()
    private var _tagsList: List<Tag>? = null

    private var _viewModelIsReady by mutableStateOf(true)
    fun listTags(): List<Tag>? {
        return _tagsList
    }
    fun loadTags() {
        _viewModelIsReady = false
        viewModelScope.launch {
            _tagsList = _tagRepository.listTags()
            _viewModelIsReady = true
        }
    }

    fun isReady(): Boolean {
        return _viewModelIsReady
    }
}