package com.example.journey.data.models

data class UserResponse(
    val id: Int,
    val fullName: String,
    val email: String,
    val position: String,
    val role: String,
    val nuts: Int,
    val superpower: Superpower,
    val tags: List<Tag>,
    val bio: String,
    val interactionsCount: Int,
    val journeysCompleted: Int,
    val publishesCount: Int,
    val likes: List<ReactionResponse>,
    val score: Int
)