package com.example.journey.data.models

data class UserResponse(
    val fullName: String,
    val email: String,
    val position: String,
    val role: String,
    val nuts: Int,
    val superpower: String,
    val tags: List<String>,
    val biography: String
)