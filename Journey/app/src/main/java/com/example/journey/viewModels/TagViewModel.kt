package com.example.journey.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.journey.data.models.Tag
import com.example.journey.data.repository.TagRepository
import kotlinx.coroutines.launch

private val lista = listOf(
    Tag(1, "Transformar"),
    Tag(2, "Cuidar"),
    Tag(3, "Diversificar"),
    Tag(4, "Respeitar"),
    Tag(5, "Produzir bem"),
    Tag(6, "Criatividade"),
    Tag(7, "Inovação"),
    Tag(8, "Excelência"),
    Tag(9, "Reconhecimento"),
    Tag(10, "Surpreender"),
    Tag(11, "Novidade"),
    Tag(12, "Expansão"),
    Tag(13, "Manutenção"),
    Tag(14, "Agilidade"),
    Tag(15, "Comportamento"),
    Tag(16, "Transparência"),
    Tag(17, "Expectativas x Restrições"),
    Tag(18, "Desenvolvimento pessoal"),
    Tag(19, "Softskills"),
    Tag(20, "Aprendizado contínuo"),
    Tag(21, "Evolução"),
    Tag(22, "Mercado"),
    Tag(23,"Constância"),
    Tag(24, "Diálogo"),
    Tag(25, "Compartilhar")
)

class TagViewModel: ViewModel() {
    private val _tagRepository = TagRepository()
    private var _tagsList: List<Tag>? = lista

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