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
    val missionsCompleted: Int,
    val likes: List<ReactionResponse>,
    val score: Int
)