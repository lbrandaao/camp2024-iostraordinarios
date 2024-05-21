package com.example.journey.data.models

data class ReactionResponse (
    val id: Int,
    val type: String,
    val publish: PostResponse
)