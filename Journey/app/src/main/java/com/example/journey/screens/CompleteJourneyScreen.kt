package com.example.journey.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.journey.R
import com.example.journey.components.textfields.CustomDatePicker
import com.example.journey.components.textfields.CustomTextField
import com.example.journey.data.models.UserResponse
import com.example.journey.ui.theme.Poppins
import com.example.journey.ui.theme.PrimaryBackgroundColor
import com.example.journey.viewModels.JourneyViewModel
import com.example.journey.viewModels.UserViewModel

@Composable
fun CompleteJourneyScreen(
    userViewModel: UserViewModel,
    journeyViewModel: JourneyViewModel,
    onBackClick: () -> Unit,
    onJourneyCompleted: () -> Unit
) {
    if (journeyViewModel.getSelectedJourney() == null) onBackClick.invoke()

    if (userViewModel.listAllUsers() == null) userViewModel.loadAllUsers()

    if (userViewModel.isReady() && userViewModel.getAuthenticatedUser() == null) userViewModel.setAuthenticatedUser()

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = PrimaryBackgroundColor
    ) {

        if (
            userViewModel.isReady() &&
            journeyViewModel.isReady()
        ) {
            var nameQuery by remember {
                mutableStateOf("")
            }

            val allUsersList = remember { userViewModel.listAllUsers() ?: listOf() }

            var usersNameResults by remember {
                mutableStateOf(listOf<UserResponse>())
            }

            var selectedFriend by remember {
                mutableIntStateOf(-1)
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 35.dp, start = 16.dp, end = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Ícone clicável para voltar à tela anterior.",
                            tint = Color(0xFF353F38),
                            modifier = Modifier
                                .size(25.dp)
                                .clickable {
                                    onBackClick.invoke()
                                }
                        )

                        Text(
                            text = "Concluir Jornada",
                            fontFamily = Poppins,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 24.sp,
                            color = Color.Black
                        )

                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Ícone sem funcionalidade.",
                            tint = Color(0xFF353F38),
                            modifier = Modifier
                                .size(25.dp)
                        )
                    }
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp)
                            .padding(top = 70.dp),
                        verticalArrangement = Arrangement.spacedBy(40.dp)
                    ) {
                        CustomDatePicker(
                            label = "Data de conclusão da Jornada",
                            placeholder = "dd/mm/aaaa"
                        )

                        Column {
                            CustomTextField(
                                value = nameQuery,
                                onValueChange = {
                                    nameQuery = it
                                    usersNameResults = if (nameQuery.length >= 3) {
                                        allUsersList.filter { user ->
                                            user.fullName.contains(nameQuery, ignoreCase = true)
                                        }.take(3)
                                    } else {
                                        listOf()
                                    }
                                },
                                label = "Convidar amigo",
                                placeholder = "Pesquisar pessoas",
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        contentDescription = "Ícone não clicável",
                                        tint = Color(0xFF828282),
                                        modifier = Modifier
                                            .size(32.dp)
                                    )
                                },
                                modifier = Modifier
                                    .onFocusChanged {
                                        if (!it.isFocused) usersNameResults = listOf()
                                    }
                            )

                            if (usersNameResults.isNotEmpty()) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color.White, shape = RoundedCornerShape(8.dp))
                                        .padding(horizontal = 5.dp, vertical = 10.dp),
                                    verticalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    repeat(usersNameResults.size) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .border(
                                                    width = 1.dp,
                                                    color =
                                                    if (selectedFriend == usersNameResults[it].id)
                                                        Color(0xFFEB0049)
                                                    else Color(0xFFE0E0E0),
                                                    shape = RoundedCornerShape(8.dp)
                                                )
                                                .background(
                                                    Color.White,
                                                    shape = RoundedCornerShape(8.dp)
                                                )
                                                .padding(10.dp)
                                                .clickable {
                                                    selectedFriend =
                                                        if (selectedFriend == usersNameResults[it].id) {
                                                            -1
                                                        } else {
                                                            usersNameResults[it].id
                                                        }
                                                },
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(
                                                text = usersNameResults[it].fullName,
                                                fontFamily = Poppins,
                                                fontWeight = FontWeight.Normal,
                                                fontSize = 14.sp,
                                                color =
                                                if (selectedFriend == usersNameResults[it].id)
                                                    Color(0xFFEB0049)
                                                else Color.Black,
                                                modifier = Modifier.fillMaxWidth(0.75f),
                                                overflow = TextOverflow.Ellipsis
                                            )

                                            if (selectedFriend == usersNameResults[it].id) {
                                                Icon(
                                                    imageVector = Icons.Default.Clear,
                                                    contentDescription = "Ícone para remoção do convite.",
                                                    tint = Color(0xFFEB0049)
                                                )
                                            }
                                        }


                                    }
                                }
                            }
                        }

                        CustomTextField(
                            value = "",
                            onValueChange = {},
                            readOnly = true,
                            label = "Anexar comprovante",
                            placeholder = ".pdf .jpeg.",
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.upload_icon),
                                    contentDescription = "Ícone não clicável",
                                    tint = Color(0xFF828282),
                                    modifier = Modifier
                                        .size(32.dp)
                                )
                            }
                        )
                    }
                }

                item {
                    OutlinedButton(
                        onClick = {
                            onJourneyCompleted.invoke()
                        },
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color(0xFFEB0049)
                        ),
                        border = null,
                        shape = RoundedCornerShape(24.dp),
                        modifier = Modifier
                            .padding(top = 80.dp, bottom = 20.dp)
                            .size(width = 176.dp, height = 40.dp)
                    ) {
                        Text(
                            text = "Enviar",
                            fontFamily = Poppins,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(120.dp),
                    color = Color.Black,
                    strokeWidth = 8.dp
                )
            }
        }
    }
}


@Preview
@Composable
fun CompleteJourneyScreenPreview() {
    CompleteJourneyScreen(
        userViewModel = UserViewModel(),
        journeyViewModel = JourneyViewModel(),
        onBackClick = {},
        onJourneyCompleted = {}
    )
}