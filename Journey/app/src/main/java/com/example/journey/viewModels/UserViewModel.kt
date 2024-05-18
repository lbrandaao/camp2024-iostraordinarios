package com.example.journey.viewModels

import androidx.lifecycle.ViewModel
import com.example.journey.data.models.NewUserRequest
import com.example.journey.data.repository.UserRepository

class UserViewModel: ViewModel(){
    private val _userRepository = UserRepository()

    private var _newUserFullName: String = ""
    private var _newUserEmail: String = ""
    private var _newUserPassword: String = ""
    private var _newUserPosition: String = ""
    private var _newUserRole: String = ""

    fun saveNewUserData(
        fullName: String,
        email: String,
        password: String,
        position: String,
        role: String
    ) {
        _newUserFullName = fullName
        _newUserEmail = email
        _newUserPassword = password
        _newUserPosition = position
        _newUserRole = role
    }


    fun authUser(email: String, password: String): Boolean {
        return _userRepository.login(email, password)
    }

    fun createUser(
        superpowerId: Int,
        tagsIdList: List<Int>
    ): Boolean {
        return _userRepository.createUser(
            NewUserRequest(
                fullName = _newUserFullName,
                email = _newUserEmail,
                password = _newUserPassword,
                position = _newUserPosition,
                role = _newUserRole,
                superpower = superpowerId,
                tags = tagsIdList
            )
        )
    }
}