package com.example.journey.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.journey.R
import com.example.journey.components.utils.SuperpowerRankingComponent
import com.example.journey.components.utils.UserRankingComponent
import com.example.journey.ui.theme.Poppins
import com.example.journey.viewModels.SuperpowerViewModel
import com.example.journey.viewModels.UserViewModel

private val medalsResourceList = listOf(
    R.drawable.first_medal,
    R.drawable.second_medal,
    R.drawable.third_medal
)

@Composable
fun RankingScreen(
    userViewModel: UserViewModel,
    superpowerViewModel: SuperpowerViewModel,
    paddingValues: PaddingValues
) {
    if (userViewModel.listRankingUsers() == null)
        userViewModel.loadRankingUsers()

    if (superpowerViewModel.listSuperpowersRankingList() == null)
        superpowerViewModel.loadSuperpowersRankingList()

    if (
        userViewModel.isReady() &&
        superpowerViewModel.isReady()
    ) {
        var rankingSelected by remember {
            mutableIntStateOf(0)
        }

        val usersRankingList = remember {
            userViewModel.listRankingUsers() ?: listOf()
        }
        val superpowersRankingList = remember {
            superpowerViewModel.listSuperpowersRankingList() ?: listOf()
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "Ranking",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 25.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(top = 20.dp)
                )
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 40.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedButton(
                        onClick = {
                            rankingSelected = 0
                        },
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor =
                            if (rankingSelected == 0)
                                Color(0xFFEB0049)
                            else
                                Color.White
                        ),
                        border = null,
                        shape = RoundedCornerShape(24.dp),
                        modifier = Modifier
                            .padding(top = 40.dp, bottom = 20.dp)
                            .size(width = 165.dp, height = 40.dp)
                    ) {
                        Text(
                            text = "Individual",
                            fontFamily = Poppins,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color =
                            if (rankingSelected == 0)
                                Color.White
                            else
                                Color(0xFFEB0049)
                        )
                    }

                    OutlinedButton(
                        onClick = {
                            rankingSelected = 1
                        },
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor =
                            if (rankingSelected == 1)
                                Color(0xFFEB0049)
                            else
                                Color.White
                        ),
                        border = null,
                        shape = RoundedCornerShape(24.dp),
                        modifier = Modifier
                            .padding(top = 40.dp, bottom = 20.dp)
                            .size(width = 165.dp, height = 40.dp)
                    ) {
                        Text(
                            text = "Superpoderes",
                            fontFamily = Poppins,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color =
                            if (rankingSelected == 1)
                                Color.White
                            else
                                Color(0xFFEB0049)
                        )
                    }
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),
                ) {
                    if (rankingSelected == 0) {
                        repeat(3) {
                            UserRankingComponent(
                                user = usersRankingList[it],
                                medalResource = medalsResourceList[it],
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                                    .padding(bottom = 10.dp)
                            )
                        }

                        Box(
                            modifier = Modifier
                                .background(
                                    color = Color(0xFFB80039),
                                    shape = CircleShape
                                )
                                .padding(horizontal = 20.dp)

                        ) {
                            Text(
                                text = "Você",
                                fontFamily = Poppins,
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp,
                                color = Color.White,
                                modifier = Modifier
                                    .align(Alignment.Center)
                            )
                        }
                        UserRankingComponent(
                            user = userViewModel.getAuthenticatedUser()!!,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = paddingValues.calculateBottomPadding() + 5.dp)
                        )
                    } else {
                        repeat(superpowersRankingList.size - 1) {
                            SuperpowerRankingComponent(
                                superpowerRanking = superpowersRankingList[it],
                                user = userViewModel.getAuthenticatedUser()!!,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                                    .padding(bottom = 10.dp)
                            )
                        }

                        if (superpowersRankingList.isNotEmpty()) {
                            SuperpowerRankingComponent(
                                superpowerRanking = superpowersRankingList.last(),
                                user = userViewModel.getAuthenticatedUser()!!,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                                    .padding(bottom = paddingValues.calculateBottomPadding() + 10.dp)
                            )
                        }
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