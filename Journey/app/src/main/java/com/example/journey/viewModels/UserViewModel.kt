package com.example.journey.viewModels

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.journey.MainActivity
import com.example.journey.data.models.NewUserRequest
import com.example.journey.data.models.Superpower
import com.example.journey.data.models.Tag
import com.example.journey.data.models.UserResponse
import com.example.journey.data.repository.UserRepository
import kotlinx.coroutines.launch

private val user = UserResponse(
    id = 3,
    fullName = "Leonardo B. Brandão",
    email = "leonardobbrandao@hotmail.com",
    position = "Desenvolvedor Mobile",
    role = "leader",
    nuts = 1355,
    superpower = Superpower(5, "O indestrutível laço da evolução"),
    tags = listOf(
        Tag(1, "Transformar"),
        Tag(2, "Cuidar"),
        Tag(3, "Diversificar"),
        Tag(4, "Respeitar"),
        Tag(5, "Produzir bem"),
        Tag(6, "Criatividade"),
        Tag(7, "Inovação"),
        Tag(8, "Excelência"),
        Tag(9, "Reconhecimento"),
        Tag(10, "Surpreender")
    ),
    bio = "Fala, galera! Sou o Léo",
    interactionsCount = 122,
    missionsCompleted = 19,
    score = 122+19
)

private val lista = listOf(
    UserResponse(
        id = 1,
        fullName = "Ana Carolina Borges",
        email = "leonardobbrandao@hotmail.com",
        position = "Desenvolvedor Mobile",
        role = "leader",
        nuts = 1355,
        superpower = Superpower(5, "O indestrutível laço da evolução"),
        tags = listOf(
            Tag(1, "Transformar"),
            Tag(2, "Cuidar"),
            Tag(3, "Diversificar"),
            Tag(4, "Respeitar"),
            Tag(5, "Produzir bem"),
            Tag(6, "Criatividade"),
            Tag(7, "Inovação"),
            Tag(8, "Excelência"),
            Tag(9, "Reconhecimento"),
            Tag(10, "Surpreender")
        ),
        bio = "Fala, galera! Sou o Léo",
        interactionsCount = 122,
        missionsCompleted = 19,
        score = 122+19
    ),
    UserResponse(
        id = 2,
        fullName = "Pryscilla",
        email = "leonardobbrandao@hotmail.com",
        position = "Desenvolvedor Mobile",
        role = "leader",
        nuts = 1355,
        superpower = Superpower(5, "O indestrutível laço da evolução"),
        tags = listOf(
            Tag(1, "Transformar"),
            Tag(2, "Cuidar"),
            Tag(3, "Diversificar"),
            Tag(4, "Respeitar"),
            Tag(5, "Produzir bem"),
            Tag(6, "Criatividade"),
            Tag(7, "Inovação"),
            Tag(8, "Excelência"),
            Tag(9, "Reconhecimento"),
            Tag(10, "Surpreender")
        ),
        bio = "Fala, galera! Sou o Léo",
        interactionsCount = 122,
        missionsCompleted = 19,
        score = 122+19
    ),
    UserResponse(
        id = 3,
        fullName = "Leonardo B. Brandão",
        email = "leonardobbrandao@hotmail.com",
        position = "Desenvolvedor Mobile",
        role = "leader",
        nuts = 1355,
        superpower = Superpower(5, "O indestrutível laço da evolução"),
        tags = listOf(
            Tag(1, "Transformar"),
            Tag(2, "Cuidar"),
            Tag(3, "Diversificar"),
            Tag(4, "Respeitar"),
            Tag(5, "Produzir bem"),
            Tag(6, "Criatividade"),
            Tag(7, "Inovação"),
            Tag(8, "Excelência"),
            Tag(9, "Reconhecimento"),
            Tag(10, "Surpreender")
        ),
        bio = "Fala, galera! Sou o Léo",
        interactionsCount = 122,
        missionsCompleted = 19,
        score = 122+19
    ),
    UserResponse(
        id = 4,
        fullName = "Davi",
        email = "leonardobbrandao@hotmail.com",
        position = "Desenvolvedor Mobile",
        role = "leader",
        nuts = 1355,
        superpower = Superpower(5, "O indestrutível laço da evolução"),
        tags = listOf(
            Tag(1, "Transformar"),
            Tag(2, "Cuidar"),
            Tag(3, "Diversificar"),
            Tag(4, "Respeitar"),
            Tag(5, "Produzir bem"),
            Tag(6, "Criatividade"),
            Tag(7, "Inovação"),
            Tag(8, "Excelência"),
            Tag(9, "Reconhecimento"),
            Tag(10, "Surpreender")
        ),
        bio = "Fala, galera! Sou o Léo",
        interactionsCount = 122,
        missionsCompleted = 19,
        score = 122+19
    ),
    UserResponse(
        id = 5,
        fullName = "Gabriel Mendes",
        email = "leonardobbrandao@hotmail.com",
        position = "Desenvolvedor Mobile",
        role = "leader",
        nuts = 1355,
        superpower = Superpower(5, "O indestrutível laço da evolução"),
        tags = listOf(
            Tag(1, "Transformar"),
            Tag(2, "Cuidar"),
            Tag(3, "Diversificar"),
            Tag(4, "Respeitar"),
            Tag(5, "Produzir bem"),
            Tag(6, "Criatividade"),
            Tag(7, "Inovação"),
            Tag(8, "Excelência"),
            Tag(9, "Reconhecimento"),
            Tag(10, "Surpreender")
        ),
        bio = "Fala, galera! Sou o Léo",
        interactionsCount = 122,
        missionsCompleted = 19,
        score = 122+19
    ),
    UserResponse(
        id = 6,
        fullName = "Wanessa",
        email = "leonardobbrandao@hotmail.com",
        position = "Desenvolvedor Mobile",
        role = "leader",
        nuts = 1355,
        superpower = Superpower(5, "O indestrutível laço da evolução"),
        tags = listOf(
            Tag(1, "Transformar"),
            Tag(2, "Cuidar"),
            Tag(3, "Diversificar"),
            Tag(4, "Respeitar"),
            Tag(5, "Produzir bem"),
            Tag(6, "Criatividade"),
            Tag(7, "Inovação"),
            Tag(8, "Excelência"),
            Tag(9, "Reconhecimento"),
            Tag(10, "Surpreender")
        ),
        bio = "Fala, galera! Sou o Léo",
        interactionsCount = 122,
        missionsCompleted = 19,
        score = 122+19
    )
)
class UserViewModel : ViewModel() {
    private val _userRepository = UserRepository()

    private var _newUserFullName: String = ""
    private var _newUserEmail: String = ""
    private var _newUserPassword: String = ""
    private var _newUserPosition: String = ""
    private var _newUserRole: String = ""

    private var _authenticatedUser: UserResponse? = user

    private var _allUsers: List<UserResponse>? = lista

    private var _viewModelIsReady by mutableStateOf(true)

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

    fun createUser(
        superpowerId: Int,
        tagsIdList: List<Int>,
        context: MainActivity,
        onRegistrationConfirm: () -> Unit
    ) {
        val newUserRequest = NewUserRequest(
            fullName = _newUserFullName,
            email = _newUserEmail,
            password = _newUserPassword,
            position = _newUserPosition,
            role = _newUserRole,
            superpowerId = superpowerId,
            tagIds = tagsIdList
        )
        viewModelScope.launch {
            val newUserIsCreated = _userRepository.createUser(newUserRequest)

            if (newUserIsCreated) {
                authenticateUser(
                    newUserRequest.email,
                    newUserRequest.password,
                    context,
                    onRegistrationConfirm
                )
            } else Toast.makeText(
                context,
                "Algo deu errado.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun authenticateUser(
        email: String,
        password: String,
        context: MainActivity,
        onAuthConfirm: () -> Unit
    ) {
        _viewModelIsReady = false
        viewModelScope.launch {
            val userIsAuthenticated = _userRepository.authenticateUser(email, password)
            if (userIsAuthenticated) {
                _authenticatedUser = _userRepository.getAuthenticatedUser()
                onAuthConfirm.invoke()
            } else {
                Toast.makeText(
                    context,
                    "Email ou senha inválidos.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            _viewModelIsReady = true
        }
    }

    fun setAuthenticatedUser() {
        _viewModelIsReady = false
        viewModelScope.launch {
            _authenticatedUser = _userRepository.getAuthenticatedUser()
            _viewModelIsReady = true
        }
    }

    fun getAuthenticatedUser(): UserResponse? {
        return _authenticatedUser
    }

    fun listAllUsers(): List<UserResponse>? {
        return _allUsers?.filter { it.id != _authenticatedUser?.id }
    }

    fun loadAllUsers() {
        _viewModelIsReady = false
        viewModelScope.launch {
            _allUsers = _userRepository.listAllUsers()
            _viewModelIsReady = true
        }
    }

    fun isReady(): Boolean {
        return _viewModelIsReady
    }
}