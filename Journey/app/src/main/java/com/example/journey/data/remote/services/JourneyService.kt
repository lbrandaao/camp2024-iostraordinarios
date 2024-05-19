package com.example.journey.data.remote.services

import com.example.journey.data.models.Journey
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface JourneyService {
    @GET("journeys")
    suspend fun getAllJourneys(
        @Header("Authorization") token: String
    ): Response<List<Journey>>

    @POST("journeys")
    suspend fun createJourney(
        @Header("Authorization") token: String,
        @Body newJourney: Journey
    ): Response<Journey>

    @GET("journeys/{id}")
    suspend fun getJourney(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<Journey>
}