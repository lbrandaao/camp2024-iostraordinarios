package com.example.journey.data.remote.services

import com.example.journey.data.models.Tag
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface TagService {
    @GET("tags")
    suspend fun getAllTags(
        @Header("Authorization") token: String
    ): Response<List<Tag>>

    @GET("tags/{id}")
    suspend fun getTag(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<Tag>
}