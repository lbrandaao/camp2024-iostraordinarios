package com.example.journey.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.journey.MainActivity
import com.example.journey.data.models.Post
import com.example.journey.data.repository.PostRepository
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {
    private val _postRepository = PostRepository()
    private var _postSelected: Post? = null
    private var _postsList: List<Post>? = null

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