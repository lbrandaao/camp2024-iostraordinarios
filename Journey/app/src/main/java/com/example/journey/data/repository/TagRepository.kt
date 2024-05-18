package com.example.journey.data.repository

import com.example.journey.data.models.Tag
import com.example.journey.data.remote.services.TagService

class TagRepository {
    private val _tagService = TagService()

    fun listTags(): List<Tag> {
        return _tagService.listTags()
    }
}