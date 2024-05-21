package com.example.journey.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.journey.MainActivity
import com.example.journey.R
import com.example.journey.components.textfields.CustomDropDownMenuCreation
import com.example.journey.components.textfields.CustomLongTextField
import com.example.journey.components.textfields.CustomTextField
import com.example.journey.data.models.JourneyResponse
import com.example.journey.data.models.NewJourneyRequest
import com.example.journey.data.models.NewPostRequest
import com.example.journey.data.models.PostResponse
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
) {
    if (
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
    ) {
        var entityCreated by remember {
            mutableStateOf("")
        }

        var title by remember {
            mutableStateOf("")
        }

        var description by remember {
            mutableStateOf("")
        }

        val allSuperpowersList = remember { superpowerViewModel.listSuperpowers() ?: listOf() }

        var superpowerQuery by remember {
            mutableStateOf("")
        }

        var superpowerQueryResults by remember {
            mutableStateOf(listOf<Superpower>())
        }

        val selectedSuperpowers = remember {
            mutableStateListOf<Superpower>()
        }

        val allTagsList = remember { tagViewModel.listTags() ?: listOf() }

        var tagQuery by remember {
            mutableStateOf("")
        }

        var tagQueryResults by remember {
            mutableStateOf(listOf<Tag>())
        }

        val selectedTags = remember {
            mutableStateListOf<Tag>()
        }

        var nutsText by remember {
            mutableStateOf("")
        }

        val creationOptions = remember {
            if (
                userViewModel.getAuthenticatedUser()?.role == "user"
            ) listOf("Post") else listOf("Post", "Jornada")
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 25.dp)
        ) {

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = paddingValues.calculateTopPadding() + 50.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
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
            }

            item {
                CustomTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = "",
                    placeholder = "Título",
                    modifier = Modifier
                        .padding(top = 10.dp)
                )
            }

            item {
                CustomLongTextField(
                    value = description,
                    onValueChange = { description = it },
                    placeholder = "Descrição",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .height(160.dp)
                )
            }

            item {
                CustomTextField(
                    value = tagQuery,
                    onValueChange = {
                        tagQuery = it
                        tagQueryResults = if (tagQuery.length >= 3) {
                            allTagsList.filter { tag ->
                                tag.name.contains(tagQuery, ignoreCase = true)
                            }.take(3)
                        } else {
                            listOf()
                        }
                    },
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
                    },
                    modifier = Modifier
                        .onFocusChanged {
                            if (!it.isFocused) {
                                tagQueryResults = listOf()
                            }
                        }
                )

                if (tagQueryResults.isNotEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White, shape = RoundedCornerShape(8.dp))
                            .padding(horizontal = 5.dp, vertical = 10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        repeat(tagQueryResults.size) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(
                                        width = 1.dp,
                                        color =
                                        if (selectedTags.contains(tagQueryResults[it])) Color(
                                            0xFF1448B8
                                        )
                                        else Color(0xFFE0E0E0),
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                                    .padding(10.dp)
                                    .clickable {
                                        if (selectedTags.contains(tagQueryResults[it])) {
                                            selectedTags -= tagQueryResults[it]
                                        } else {
                                            selectedTags += tagQueryResults[it]
                                        }
                                    },
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = tagQueryResults[it].name,
                                    fontFamily = Poppins,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp,
                                    color = if (selectedTags.contains(tagQueryResults[it])) Color(
                                        0xFF1448B8
                                    ) else Color.Black,
                                    modifier = Modifier.fillMaxWidth(0.75f),
                                    overflow = TextOverflow.Ellipsis
                                )

                                if (selectedTags.contains(tagQueryResults[it])) {
                                    Icon(
                                        imageVector = Icons.Default.Clear,
                                        contentDescription = "Ícone para remoção da tag",
                                        tint = Color(0xFF1448B8)
                                    )
                                }
                            }


                        }
                    }
                }
            }

            item {
                CustomTextField(
                    value = superpowerQuery,
                    onValueChange = {
                        superpowerQuery = it
                        superpowerQueryResults = if (superpowerQuery.length >= 3) {
                            allSuperpowersList.filter { superpower ->
                                superpower.name.contains(superpowerQuery, ignoreCase = true)
                            }.take(3)
                        } else {
                            listOf()
                        }
                    },
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
                    },
                    modifier = Modifier
                        .onFocusChanged {
                            if (!it.isFocused) {
                                superpowerQueryResults = listOf()
                            }
                        }
                )

                if (superpowerQueryResults.isNotEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White, shape = RoundedCornerShape(8.dp))
                            .padding(horizontal = 5.dp, vertical = 10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        repeat(superpowerQueryResults.size) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(
                                        width = 1.dp,
                                        color =
                                        if (selectedSuperpowers.contains(superpowerQueryResults[it])) Color(
                                            0xFF1448B8
                                        )
                                        else Color(0xFFE0E0E0),
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                                    .padding(10.dp)
                                    .clickable {
                                        if (selectedSuperpowers.contains(superpowerQueryResults[it])) {
                                            selectedSuperpowers -= superpowerQueryResults[it]
                                        } else {
                                            selectedSuperpowers += superpowerQueryResults[it]
                                        }
                                    },
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = superpowerQueryResults[it].name,
                                    fontFamily = Poppins,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp,
                                    color = if (selectedSuperpowers.contains(superpowerQueryResults[it])) Color(
                                        0xFF1448B8
                                    ) else Color.Black,
                                    modifier = Modifier.fillMaxWidth(0.75f),
                                    overflow = TextOverflow.Ellipsis
                                )

                                if (selectedSuperpowers.contains(superpowerQueryResults[it])) {
                                    Icon(
                                        imageVector = Icons.Default.Clear,
                                        contentDescription = "Ícone para remoção do superpoder",
                                        tint = Color(0xFF1448B8)
                                    )
                                }
                            }


                        }
                    }
                }
            }

            if (entityCreated == "Jornada") {
                item {
                    CustomTextField(
                        value = nutsText,
                        onValueChange = {
                            if (
                                it.isEmpty() ||
                                it.last().isDigit()
                                ) nutsText = it
                        },
                        label = "",
                        placeholder = "Adicionar número de nozes",
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.nut_icon),
                                contentDescription = "Ícone não clicável",
                                tint = Color(0xFF828282),
                                modifier = Modifier
                                    .size(32.dp)
                            )
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        )
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = paddingValues.calculateBottomPadding() + 30.dp,
                            top = 60.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
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
                                if (entityCreated == "Post") {
                                    postViewModel.createPost(
                                        context,
                                        newPost = NewPostRequest(
                                            title = title,
                                            description = description,
                                            superpowersId = selectedSuperpowers.map { it.id },
                                            tagsId = selectedTags.map { it.id }
                                        ),
                                        onCreateConfirm = onCreationConfirm
                                    )
                                } else {
                                    if (nutsText.isNotBlank()) {
                                        journeyViewModel.createJourney(
                                            context,
                                            newJourney = NewJourneyRequest(
                                                title = title,
                                                description = description,
                                                nuts = nutsText.toInt(),
                                                superpowersId = selectedSuperpowers.map { it.id },
                                                tagsId = selectedTags.map { it.id }
                                            ),
                                            onCreateConfirm = onCreationConfirm
                                        )
                                    } else Toast.makeText(
                                        context,
                                        "Preencha todos os campos",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            } else Toast.makeText(
                                context,
                                "Preencha todos os campos",
                                Toast.LENGTH_SHORT
                            ).show()
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