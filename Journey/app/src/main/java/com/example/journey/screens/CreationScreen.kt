package com.example.journey.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.journey.MainActivity
import com.example.journey.components.textfields.CustomDropDownMenuCreation
import com.example.journey.components.textfields.CustomLongTextField
import com.example.journey.components.textfields.CustomTextField
import com.example.journey.data.models.Journey
import com.example.journey.data.models.Post
import com.example.journey.data.models.Superpower
import com.example.journey.data.models.Tag
import com.example.journey.ui.theme.Poppins
import com.example.journey.viewModels.JourneyViewModel
import com.example.journey.viewModels.PostViewModel
import com.example.journey.viewModels.SuperpowerViewModel
import com.example.journey.viewModels.TagViewModel
import com.example.journey.viewModels.UserViewModel

@Composable
fun CreationScreen(
    context: MainActivity,
    paddingValues: PaddingValues,
    userViewModel: UserViewModel,
    journeyViewModel: JourneyViewModel,
    postViewModel: PostViewModel,
    tagViewModel: TagViewModel,
    superpowerViewModel: SuperpowerViewModel,
    onBackButtonClick: () -> Unit,
    onCreationConfirm: () -> Unit,
){
    /*if (
        tagViewModel.listTags() == null ||
        superpowerViewModel.listSuperpowers() == null
    ) {
        tagViewModel.loadTags()
        superpowerViewModel.loadSuperpowers()
    }

    if (userViewModel.getAuthenticatedUser() == null) userViewModel.setAuthenticatedUser()

    if (
        userViewModel.isReady() &&
        tagViewModel.isReady() &&
        superpowerViewModel.isReady()
        ) {*/
        var entityCreated by remember {
            mutableStateOf("")
        }

        var title by remember {
            mutableStateOf("")
        }

        var description by remember {
            mutableStateOf("")
        }

        val selectedTags = mutableListOf<Tag>()

        val selectedSuperpowers = mutableListOf<Superpower>()

        val creationOptions = if (
            userViewModel.getAuthenticatedUser()?.role == "user"
            ) listOf("Post") else listOf("Post", "Jornada")

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 25.dp)
                .padding(top = paddingValues.calculateTopPadding() + 50.dp)
        ) {

            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "Criar",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    color = Color.Black
                )

                CustomDropDownMenuCreation(
                    value = entityCreated,
                    onItemClick = { entityCreated = it },
                    placeholder = "Novo",
                    dropDownItemsTextList = creationOptions,
                    modifier = Modifier
                        .width(135.dp)
                )
            }

            CustomTextField(
                value = title,
                onValueChange = { title = it },
                label = "",
                placeholder = "Título",
                modifier = Modifier
                    .padding(top = 10.dp)
            )

            CustomLongTextField(
                value = description,
                onValueChange = {description = it},
                placeholder = "Descrição",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .height(160.dp)
            )
            
            CustomTextField(
                value = "",
                onValueChange = {},
                label = "",
                placeholder = "Adicionar tags",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Ícone não clicável",
                        tint = Color(0xFF828282),
                        modifier = Modifier
                            .size(32.dp)
                    )
                }
            )

            CustomTextField(
                value = "",
                onValueChange = {},
                label = "",
                placeholder = "Adicionar superpoderes",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Ícone não clicável",
                        tint = Color(0xFF828282),
                        modifier = Modifier
                            .size(32.dp)
                    )
                }
            )

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp, top = 60.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                OutlinedButton(
                    onClick = {
                        onBackButtonClick.invoke()
                    },
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.Transparent
                    ),
                    border = BorderStroke(1.dp, Color(0xFF759CF0)),
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier
                        .size(width = 160.dp, height = 40.dp)
                ) {
                    Text(
                        text = "Voltar",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = Color(0xFF759CF0)
                    )
                }

                OutlinedButton(
                    onClick = {
                        if (
                            entityCreated.isNotBlank() &&
                            title.isNotBlank() &&
                            description.isNotBlank()
                            ) {
                            if (entityCreated == "Jornada")
                                journeyViewModel.createJourney(
                                    context,
                                    journey = Journey(
                                        title = title,
                                        description = description,
                                        nuts = 20,
                                        creator = userViewModel.getAuthenticatedUser()!!,
                                        superpowers = selectedSuperpowers,
                                        tags = selectedTags
                                    ) ,
                                    onCreateConfirm = onCreationConfirm
                                )
                            else
                                postViewModel.createPost(
                                    context,
                                    post = Post(
                                        title = title,
                                        description = description,
                                        creator = userViewModel.getAuthenticatedUser()!!,
                                        superpowers = selectedSuperpowers,
                                        tags = selectedTags
                                    ) ,
                                    onCreateConfirm = onCreationConfirm
                                )
                        }
                    },
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color(0xFF306BE9)
                    ),
                    border = BorderStroke(1.dp, Color(0xFF306BE9)),
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier
                        .size(width = 160.dp, height = 40.dp)
                ) {
                    Text(
                        text = "Publicar",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }

        }
   /* } else {
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
    }*/
}

@Preview
@Composable
fun CreationScreenPreview() {
    CreationScreen(
        context = MainActivity(),
        paddingValues = PaddingValues(),
        userViewModel = UserViewModel(),
        journeyViewModel = JourneyViewModel(),
        postViewModel = PostViewModel(),
        tagViewModel = TagViewModel(),
        superpowerViewModel = SuperpowerViewModel(),
        onBackButtonClick = {}
    ) {
    }
}