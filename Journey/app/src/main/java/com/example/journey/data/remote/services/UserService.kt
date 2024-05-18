package com.example.journey.data.remote.services

import com.example.journey.data.models.NewUserRequest
import com.example.journey.data.models.UserResponse

class UserService {
    private var _userResponseLists = mutableListOf(
        UserResponse(
            fullName = "Ana Carolina M.",
            email = "",
            position = "",
            role = "",
            nuts = 0,
            superpower = "A fabulosa flecha da agilidade",
            tags = listOf<String>(),
            biography = ""
        ),
        UserResponse(
            fullName = "Mônica Araújo",
            email = "",
            position = "",
            role = "",
            nuts = 0,
            superpower = "O impenetrável escudo do cuidado",
            tags = listOf<String>(),
            biography = ""
        )
    )

    private val _userPlaceholder = UserResponse(
        "Leonardo B. Brandão",
        email = "leonardobbrandao@hotmail.com",
        position = "Desenvolvedor Mobile",
        role = "user",
        nuts = 2341,
        superpower = "O indestrutível laço da evolução",
        tags = listOf(
            "Inovação",
            "Desenvolvimento pessoal",
            "Surpreender",
            "Mercado",
            "Excelência",
            "Criatividade"
        ),
        biography = "Olá Pessoal! Sou o Léo e essa Biografia é somente um teste para algumas coisas " +
                "e ver o preenchimento do campo ok não liguem"
    )

    fun getUser(id: Int): UserResponse {
        return _userResponseLists[id]
    }

    fun createUser(newUserRequest: NewUserRequest) {
        /*TODO*/
    }

    fun auth(token: String): UserResponse {
        return _userPlaceholder
    }
    fun login(email: String, password: String) {

    }
}