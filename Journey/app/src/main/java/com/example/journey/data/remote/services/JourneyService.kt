package com.example.journey.data.remote.services

import com.example.journey.data.models.JourneyResponse
import com.example.journey.data.models.NewJourneyRequest
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface JourneyService {
    @GET("journeys")
    suspend fun getAllJourneys(
        @Header("Authorization") token: String
    ): Response<List<JourneyResponse>>

    @POST("journeys")
    suspend fun createJourney(
        @Header("Authorization") token: String,
        @Body newJourney: NewJourneyRequest
    ): Response<JourneyResponse>

    @GET("journeys/{id}")
    suspend fun getJourney(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<JourneyResponse>

    @POST("journeys/{id}/join")
    suspend fun joinJourney(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<ResponseBody>

    @PATCH("journeys/{journeyId}/complete")
    suspend fun completeJourney(
        @Header("Authorization") token: String,
        @Path("journeyId") id: Int
    ): Response<ResponseBody>
}