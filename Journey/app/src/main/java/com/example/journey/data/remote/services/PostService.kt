package com.example.journey.data.remote.services

import com.example.journey.data.models.NewPostRequest
import com.example.journey.data.models.PostResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface PostService {
    @GET("publishes")
    suspend fun getAllPosts(
        @Header("Authorization") token: String
    ): Response<List<PostResponse>>

    @POST("publishes")
    suspend fun createPost(
        @Header("Authorization") token: String,
        @Body newPost: NewPostRequest
    ): Response<PostResponse>

    @GET("publishes/{id}")
    suspend fun getPost(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<PostResponse>
}