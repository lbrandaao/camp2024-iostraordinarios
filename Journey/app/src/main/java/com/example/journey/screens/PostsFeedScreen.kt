package com.example.journey.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.journey.components.textfields.CustomTextField
import com.example.journey.components.utils.PostComponentFeed
import com.example.journey.ui.theme.Poppins
import com.example.journey.ui.theme.PrimaryBackgroundColor
import com.example.journey.viewModels.PostViewModel
import com.example.journey.viewModels.UserViewModel

@Composable
fun PostsFeedScreen(
    userViewModel: UserViewModel,
    postViewModel: PostViewModel,
    onBackButtonClick: () -> Unit
) {
    //if (userViewModel.getAuthenticatedUser() == null) userViewModel.setAuthenticatedUser()

    //if (postViewModel.listPosts() == null) postViewModel.loadPosts()

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = PrimaryBackgroundColor
    ) {
        if (
            userViewModel.isReady() &&
            postViewModel.isReady()
        ) {
            val postsList =  remember { postViewModel.listPosts()?: listOf() }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                item {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 35.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Ícone clicável para voltar para tela anterior.",
                            tint = Color.Black,
                            modifier = Modifier
                                .clickable {
                                    onBackButtonClick.invoke()
                                }
                        )

                        Text(
                            text = "Seu feed",
                            fontFamily = Poppins,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 24.sp,
                            color = Color.Black
                        )

                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Ícone não clicável.",
                            tint = Color.Black
                        )
                    }
                }

                item {
                    CustomTextField(
                        value = "" ,
                        onValueChange = {},
                        label = "",
                        placeholder = "Pesquisar tags, pessoas ou posts",
                        readOnly = true,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Ícone não clicável.",
                                tint = Color(0xFF828282),
                                modifier = Modifier
                                    .size(35.dp)
                            )
                        },
                        modifier = Modifier
                            .padding(bottom = 32.dp)
                    )
                }

                items(postsList.size) {
                    PostComponentFeed(
                        post = postsList[it],
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                    )
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
fun PostsFeedScreenPreview() {
    PostsFeedScreen(
        userViewModel = UserViewModel(),
        postViewModel = PostViewModel(),
        onBackButtonClick = {}
    )
}