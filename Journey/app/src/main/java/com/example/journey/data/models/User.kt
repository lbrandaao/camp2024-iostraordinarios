package com.example.journey.data.models

data class User(
    val full_name: String,
    val email: String,
    val position: String,
    val role: String,
    val nuts: Int,
    val superpower_id: Int,
    val tags: List<Tag>,
    val biography: String
)