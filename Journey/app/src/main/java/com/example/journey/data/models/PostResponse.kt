package com.example.journey.data.models

data class PostResponse(
    val id: Int,
    val title: String,
    val description: String,
    val creator: UserResponse,
    val superpowers: List<Superpower>,
    val tags: List<Tag>
)
