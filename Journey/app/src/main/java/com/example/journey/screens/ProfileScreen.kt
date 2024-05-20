package com.example.journey.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.journey.R
import com.example.journey.ui.theme.Poppins
import com.example.journey.viewModels.UserViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileScreen(
    paddingValues: PaddingValues,
    userViewModel: UserViewModel
) {
    if (userViewModel.getAuthenticatedUser() == null) userViewModel.setAuthenticatedUser()

    if (userViewModel.isReady()) {
        val authenticatedUser = userViewModel.getAuthenticatedUser()!!

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {
                Icon(
                    painter = painterResource(id = R.drawable.profile_icon),
                    contentDescription = "Ícone de perfil não clicável",
                    modifier = Modifier
                        .size(75.dp)
                        .background(color = Color(0xFFFF85AB), shape = CircleShape)
                        .padding(10.dp),
                    tint = Color.Black
                )
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = authenticatedUser.fullName,
                        fontFamily = Poppins,
                        fontSize = 23.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .padding(top = 8.dp)
                    )

                    Row(
                        modifier = Modifier
                            .size(width = 85.dp, height = 40.dp)
                            .background(
                                color = Color(0xFFFF85AB),
                                shape = CircleShape
                            )
                            .padding(horizontal = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = authenticatedUser.nuts.toString(),
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(top = 3.dp)
                        )

                        Icon(
                            painter = painterResource(id = R.drawable.nut_icon),
                            contentDescription = "Ícone não clicável de uma noz.",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(30.dp)
                        )
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 25.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = authenticatedUser.position,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Medium,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .padding(top = 5.dp),
                        color = Color.Black
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.save_icon),
                            contentDescription = "Ícone não clicável para itens salvos",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(20.dp)
                        )

                        Text(
                            text = "Itens salvos",
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                            color = Color.Black,
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier
                                .padding(top = 2.dp)
                        )
                    }
                }
            }

            item {
                Text(
                    text = authenticatedUser.bio,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                )
            }

            item {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy((-5).dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                ) {


                    authenticatedUser.tags.forEach {
                        AssistChip(
                            onClick = {},
                            label = {
                                Text(
                                    text = it.name,
                                    fontFamily = Poppins,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 10.sp,
                                    color = Color(0xFF1448B8)
                                )
                            },
                            border = AssistChipDefaults.assistChipBorder(
                                borderColor = Color(0xFF1448B8),
                                borderWidth = 1.dp
                            ),
                            shape = RoundedCornerShape(100.dp),
                            colors = AssistChipDefaults.assistChipColors(
                                containerColor = Color.Transparent
                            )
                        )
                    }

                    AssistChip(
                        onClick = {},
                        label = {
                            Text(
                                text = authenticatedUser.superpower.name,
                                fontFamily = Poppins,
                                fontWeight = FontWeight.Medium,
                                fontSize = 10.sp,
                                color = Color(0xFFEB0049)
                            )
                        },
                        border = AssistChipDefaults.assistChipBorder(
                            borderColor = Color(0xFFEB0049),
                            borderWidth = 1.dp
                        ),
                        shape = RoundedCornerShape(100.dp),
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = Color.Transparent
                        )
                    )


                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .padding(top = 15.dp, bottom = paddingValues.calculateBottomPadding())
                        .background(
                            color = Color(0xFFFFE5ED),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(vertical = 20.dp, horizontal = 15.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Sua jornada",
                            fontFamily = Poppins,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 24.sp,
                            color = Color.Black,
                        )
                    }

                    Text(
                        text = "Você concluiu +" +
                                authenticatedUser.missionsCompleted.toString() +
                                " jornadas no último mês!",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 30.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier
                                .size(width = 145.dp, height = 90.dp)
                                .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                                .padding(vertical = 5.dp, horizontal = 15.dp)
                        ) {
                            Row (
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Text(
                                    text = authenticatedUser.missionsCompleted.toString(),
                                    fontFamily = Poppins,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp,
                                    color = Color.Black,
                                    modifier = Modifier
                                        .padding(top = 5.dp)
                                )

                                Icon(
                                    imageVector = Icons.Default.Create,
                                    contentDescription = "Ícone não clicável de um lápis.",
                                    tint = Color.Black
                                )
                            }
                            Text(
                                text = "Posts criados",
                                fontFamily = Poppins,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp,
                                color = Color.Black
                            )
                        }

                        Column(
                            modifier = Modifier
                                .size(width = 145.dp, height = 90.dp)
                                .background(
                                    color = Color(0xFFEB0049),
                                    shape = RoundedCornerShape(20.dp)
                                )
                                .padding(vertical = 5.dp, horizontal = 15.dp)
                        ) {
                            Row (
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Text(
                                    text = authenticatedUser.interactionsCount.toString(),
                                    fontFamily = Poppins,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp,
                                    color = Color.White,
                                    modifier = Modifier
                                        .padding(top = 5.dp)
                                )

                                Icon(
                                    painter = painterResource(id = R.drawable.medal_icon),
                                    contentDescription = "Ícone não clicável de um lápis.",
                                    tint = Color.White
                                )
                            }
                            Text(
                                text = "Engajamentos",
                                fontFamily = Poppins,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp,
                                color = Color.White
                            )
                        }
                    }
                }
            }

        }
    }

}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        paddingValues = PaddingValues(),
        userViewModel = UserViewModel()
    )
}