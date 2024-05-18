package com.example.journey.data.repository

import com.example.journey.data.models.Superpower
import com.example.journey.data.remote.services.SuperpowerService

class SuperpowerRepository {
    private val _superpowerService = SuperpowerService()

    fun listSuperpowers(): List<Superpower> {
        return _superpowerService.listSuperpowers()
    }
}