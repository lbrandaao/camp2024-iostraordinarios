package com.example.journey.data.remote.services

import com.example.journey.data.models.Superpower

class SuperpowerService {
    private val _superpowerList = listOf(
        Superpower(0, "O Impenetrável Escudo do Cuidado"),
        Superpower(1, "A Varinha Mágica da Transformação"),
        Superpower(2, "O Poder Infinito da Mente"),
        Superpower(3, "O Incrível Cristal do Extraordinário"),
        Superpower(4, "As Maravilhosas Asas para Inovar"),
        Superpower(5, "A Fabulosa Flecha da Agilidade"),
        Superpower(6, "O Indestrutível Laço da Evolução")
    )

    fun listSuperpowers(): List<Superpower> {
        return _superpowerList
    }

    fun getSuperpower(id: Int): Superpower {
        return _superpowerList[id]
    }
}