package com.example.journey.data.models

data class NewPostRequest(
    val title: String,
    val description: String,
    val superpowersId: List<Int>,
    val tagsId: List<Int>
)
