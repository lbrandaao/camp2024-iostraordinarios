package com.example.journey.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.journey.MainActivity
import com.example.journey.data.models.Post
import com.example.journey.data.models.Superpower
import com.example.journey.data.models.Tag
import com.example.journey.data.models.UserResponse
import com.example.journey.data.repository.PostRepository
import kotlinx.coroutines.launch


private val lista = listOf(
    Post(
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
        )

    ),
    Post(
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
        )

    ),
    Post(
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
        )

    ),
    Post(
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
        )

    )
)

class PostViewModel : ViewModel() {
    private val _postRepository = PostRepository()
    private var _postSelected: Post? = null
    private var _postsList: List<Post>? = lista

    private var _viewModelIsReady by mutableStateOf(true)

    fun setSelectedPost(post: Post) {
        _postSelected = post
    }

    fun getSelectedPost(): Post? {
        return _postSelected
    }

    fun listPosts(): List<Post>? {
        return _postsList
    }

    fun loadPosts() {
        _viewModelIsReady = false
        viewModelScope.launch {
            _postsList = _postRepository.listPosts()
            _viewModelIsReady = true
        }
    }

    fun createPost(context: MainActivity, post: Post, onCreateConfirm: () -> Unit) {
        viewModelScope.launch {
            val postIsCreated = _postRepository.createPost(post)
            if (postIsCreated) {
                loadPosts()
                onCreateConfirm.invoke()
            }
        }
    }

    fun isReady(): Boolean {
        return _viewModelIsReady
    }
}