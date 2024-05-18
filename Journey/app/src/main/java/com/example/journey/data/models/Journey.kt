package com.example.journey.data.models

data class Journey(
    val title: String,
    val description: String,
    val publisher: UserResponse,
    val superpower: String,
    val tags: List<String>,
    val nuts: Int
)
