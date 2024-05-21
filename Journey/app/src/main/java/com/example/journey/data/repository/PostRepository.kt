package com.example.journey.data.repository

import com.example.journey.data.models.NewPostRequest
import com.example.journey.data.models.NewReactionRequest
import com.example.journey.data.models.PostResponse
import com.example.journey.data.remote.RetrofitInstance
import com.example.journey.data.remote.TokenManager

class PostRepository {
    private val _postService = RetrofitInstance.postService
    suspend fun createPost(newPost: NewPostRequest): Boolean {
        val requestToken = "Bearer " + TokenManager.getToken()
        val response = _postService.createPost(
            token = requestToken,
            newPost = newPost
        )

        return response.isSuccessful
    }

    suspend fun listPosts(): List<PostResponse>? {
        val requestToken = "Bearer " + TokenManager.getToken()
        val response = _postService.getAllPosts(requestToken)

        return response.body()
    }

    suspend fun getPost(id: Int): PostResponse? {
        val requestToken = "Bearer " + TokenManager.getToken()
        val response = _postService.getPost(requestToken, id)

        return response.body()
    }

    suspend fun addReaction(newReaction: NewReactionRequest): Boolean {
        val requestToken = "Bearer " + TokenManager.getToken()
        val response = _postService.addReaction(
            requestToken,
            newReaction
        )

        return response.isSuccessful
    }
    suspend fun removeReaction(publishId: Int): Boolean {
        val requestToken = "Bearer " + TokenManager.getToken()
        val response = _postService.removeReaction(
            requestToken,
            publishId
        )

        return response.isSuccessful
    }
}