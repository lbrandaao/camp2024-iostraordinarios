package com.example.journey.viewModels

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.journey.MainActivity
import com.example.journey.data.models.NewPostRequest
import com.example.journey.data.models.NewReactionRequest
import com.example.journey.data.models.PostResponse
import com.example.journey.data.repository.PostRepository
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {
    private val _postRepository = PostRepository()
    private var _postSelected: PostResponse? = null
    private var _postsList: List<PostResponse>? = null

    private var _viewModelIsReady by mutableStateOf(true)

    fun setSelectedPost(post: PostResponse) {
        _postSelected = post
    }

    fun getSelectedPost(): PostResponse? {
        return _postSelected
    }

    fun listPosts(): List<PostResponse>? {
        return _postsList
    }

    fun loadPosts() {
        _viewModelIsReady = false
        viewModelScope.launch {
            _postsList = _postRepository.listPosts()
            _viewModelIsReady = true
        }
    }

    fun createPost(context: MainActivity, newPost: NewPostRequest, onCreateConfirm: () -> Unit) {
        viewModelScope.launch {
            val postIsCreated = _postRepository.createPost(newPost)
            if (postIsCreated) {
                loadPosts()
                onCreateConfirm.invoke()
            } else {
                Toast.makeText(
                    context,
                    "Não foi possível criar a publicação.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun addReactionOnPost(
        context: MainActivity,
        newReaction: NewReactionRequest
    ) {
        viewModelScope.launch {
            val reactionAdded = _postRepository.addReaction(newReaction)
            if (!reactionAdded)
                Toast.makeText(
                    context,
                    "Não foi possível adicionar a reação.",
                    Toast.LENGTH_SHORT
                ).show()
        }
    }

    fun isReady(): Boolean {
        return _viewModelIsReady
    }
}