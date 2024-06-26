package com.example.journey.data.models

data class NewUserRequest(
    val fullName: String,
    val email: String,
    val password: String,
    val position: String,
    val role: String,
    val superpowerId: Int,
    val tagsId: List<Int>
)
