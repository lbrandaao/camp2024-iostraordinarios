package com.example.journey.components.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.journey.R
import com.example.journey.data.models.Post
import com.example.journey.ui.theme.Poppins

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PostComponentFeed (
    post: Post
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .background(color = Color.Transparent)
            .padding(horizontal = 5.dp)
            .padding(bottom = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(35.dp)
        ){
            Icon(
                painter = painterResource(id = R.drawable.profile_icon),
                contentDescription = "Ícone de perfil não clicável",
                modifier = Modifier
                    .size(55.dp)
                    .background(color = Color(0xFFFF85AB), shape = CircleShape)
                    .padding(10.dp),
                tint = Color.Black
            )
            Column {
                Text(
                    text = post.creator.fullName,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = post.creator.superpower.name,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Medium,
                    fontSize = 10.sp,
                    color = Color.Black,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Text(
            text = post.description,
            fontFamily = Poppins,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
        )

        FlowRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalArrangement = Arrangement.spacedBy((-10).dp)
        ) {
            post.tags.forEach {
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

            post.superpowers.forEach {
                AssistChip(
                    onClick = {},
                    label = {
                        Text(
                            text = it.name,
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

        Box (
            modifier = Modifier
                .fillMaxWidth(),
        ){
            Icon(
                painter = painterResource(id = R.drawable.add_reaction_icon),
                contentDescription = "Ícone clicável para adicionar curtir publicação.",
                tint = Color.Black,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .size(25.dp)
            )

            Row (
                modifier = Modifier
                    .align(Alignment.BottomEnd),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    painter = painterResource(id = R.drawable.message_icon),
                    contentDescription = "Ícone clicável para adicionar curtir publicação.",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(25.dp)
                )

                Icon(
                    painter = painterResource(id = R.drawable.send_icon),
                    contentDescription = "Ícone clicável para adicionar curtir publicação.",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(25.dp)
                )

                Icon(
                    painter = painterResource(id = R.drawable.save_icon),
                    contentDescription = "Ícone clicável para adicionar curtir publicação.",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(25.dp)
                )
            }
        }
    }
}