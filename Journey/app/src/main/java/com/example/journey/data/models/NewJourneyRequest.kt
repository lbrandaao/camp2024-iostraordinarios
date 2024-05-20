package com.example.journey.data.models


data class NewJourneyRequest(
    val title: String,
    val description: String,
    val superpowersId: List<Int>,
    val tagsId: List<Int>,
    val nuts: Int
)