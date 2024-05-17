package com.example.journey.data.remote.services

import com.example.journey.data.models.Superpower

class SuperpowerService {
    private val _superpowerList = listOf(
        Superpower("O Impenetrável Escudo do Cuidado"),
        Superpower("A Varinha Mágica da Transformação"),
        Superpower("O Poder Infinito da Mente"),
        Superpower("O Incrível Cristal do Extraordinário"),
        Superpower("As Maravilhosas Asas para Inovar"),
        Superpower("A Fabulosa Flecha da Agilidade"),
        Superpower("O Indestrutível Laço da Evolução")
    )

    fun getSuperpower(id: Int): Superpower {
        return _superpowerList[id]
    }
}