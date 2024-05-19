package com.example.journey.data.remote.services

import com.example.journey.data.models.Superpower
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface SuperpowerService {
    @GET("superpowers")
    suspend fun getAllSuperpowers(
        @Header("Authorization") token: String
    ): Response<List<Superpower>>

    @GET("superpowers/{id}")
    suspend fun getSuperpower(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<Superpower>
}