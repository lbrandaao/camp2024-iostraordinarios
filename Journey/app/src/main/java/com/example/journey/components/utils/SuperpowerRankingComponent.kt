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
import androidx.compose.foundation.layout.width
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
import com.example.journey.data.models.SuperpowerRankingResponse
import com.example.journey.data.models.UserResponse
import com.example.journey.ui.theme.Poppins

private fun formatToThousands(value: Int): String {
    return if (value >= 1000) {
        val thousands = value / 1000
        val remainder = (value % 1000) / 100
        "$thousands.${remainder}mil"
    } else {
        value.toString()
    }
}

@Composable
fun SuperpowerRankingComponent(
    superpowerRanking: SuperpowerRankingResponse,
    user: UserResponse,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(
                color =
                if (superpowerRanking.superpower.id == user.superpower.id)
                    Color(0xFFFFCCDC)
                else
                    Color(0xFFD1DEFA),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(17.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .background(
                        color = Color(0xFF185AE6),
                        shape = CircleShape
                    )
                    .padding(10.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.trophy_icon),
                    contentDescription = "Ícone não clicável",
                    tint = Color(0xFFFF85AB),
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.Center)
                )
            }

            Text(
                text = superpowerRanking.superpower.name,
                fontFamily = Poppins,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .fillMaxWidth(0.6f)
            )
        }

        Column {
            Text(
                text = formatToThousands(superpowerRanking.points),
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color.Black
            )
            Text(
                text = "Pontos",
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                color = Color(0xFF877777),
                modifier = Modifier
                    .padding(top = 5.dp)
            )
        }
    }
}