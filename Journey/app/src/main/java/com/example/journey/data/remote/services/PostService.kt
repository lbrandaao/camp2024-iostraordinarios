package com.example.journey.data.remote.services

import com.example.journey.data.models.Post
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface PostService {
    @GET("posts")
    suspend fun getAllPosts(
        @Header("Authorization") token: String
    ): Response<List<Post>>

    @POST("posts")
    suspend fun createPost(
        @Header("Authorization") token: String,
        @Body newPost: Post
    ): Response<Post>

    @GET("posts/{id}")
    suspend fun getPost(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<Post>
}