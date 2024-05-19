package com.example.journey.data.repository

import com.example.journey.data.models.Tag
import com.example.journey.data.remote.RetrofitInstance
import com.example.journey.data.remote.TokenManager

class TagRepository {
    private val _tagService = RetrofitInstance.tagService

    suspend fun listTags(): List<Tag>? {
        val requestToken = "Bearer " + TokenManager.getToken()
        val response = _tagService.getAllTags(requestToken)

        return response.body()
    }

    suspend fun getTag(id: Int): Tag? {
        val requestToken = "Bearer " + TokenManager.getToken()
        val response = _tagService.getTag(requestToken, id)

        return response.body()
    }
}