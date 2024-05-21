package com.example.journey.components.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.journey.R
import com.example.journey.data.models.UserResponse
import com.example.journey.ui.theme.Poppins

@Composable
fun UserRankingComponent(
    user: UserResponse,
    modifier: Modifier = Modifier,
    medalResource: Int = -1,
) {
    Column(
        modifier = modifier
            .background(
                color = Color(0xFFD1DEFA),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp, bottom = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .size(55.dp)
                        .background(
                            color = Color(0xFF828282),
                            shape = CircleShape
                        )
                        .padding(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.profile_icon),
                        contentDescription = "Ícone não clicável",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.Center)
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(start = 15.dp)
                ) {
                    Text(
                        text = user.fullName,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        color = Color.Black
                    )
                    Text(
                        text = user.superpower.name,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Medium,
                        fontSize = 10.sp,
                        color = Color.Black
                    )
                }
            }

            if (medalResource >= 0){
                Image(
                    painter = painterResource(id = medalResource),
                    contentDescription = "Imagem de uma medalha"
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Média",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = Color(0xFF877777)
                )
                Text(
                    text = user.journeysCompleted.toString() + " jornadas concluídas",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }

            Column {
                Text(
                    text = "Engajamento",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = Color(0xFF877777)
                )
                Text(
                    text = user.interactionsCount.toString() +
                            " interações",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
        }
    }
}