package com.example.journey.data.models

data class Post(
    val title: String,
    val description: String,
    val creator: UserResponse,
    val superpowers: List<Superpower>,
    val tags: List<Tag>
)
