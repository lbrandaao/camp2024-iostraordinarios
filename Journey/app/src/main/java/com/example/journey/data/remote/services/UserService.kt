package com.example.journey.data.remote.services

import com.example.journey.data.models.Tag
import com.example.journey.data.models.User

class UserService {
    private var _userList = mutableListOf(
        User(
            full_name = "Ana Carolina M.",
            email = "",
            position = "",
            role = "",
            nuts = 0,
            superpower_id = 0,
            tags = listOf<Tag>(),
            biography = ""
        ),
        User(
            full_name = "Mônica Araújo",
            email = "",
            position = "",
            role = "",
            nuts = 0,
            superpower_id = 0,
            tags = listOf<Tag>(),
            biography = ""
        )
    )

    fun getUser(id: Int): User{
        return _userList[id]
    }
}