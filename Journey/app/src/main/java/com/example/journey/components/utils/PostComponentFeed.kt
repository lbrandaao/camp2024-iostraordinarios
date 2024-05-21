package com.example.journey.components.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.journey.R
import com.example.journey.data.models.PostResponse
import com.example.journey.ui.theme.Poppins

private enum class Reaction(val color: Color, val resource: Int) {
    LIKE(Color.Blue, R.drawable.likereaction_icon),
    HEART(Color.Red, R.drawable.heartreaction_icon),
    CLAPS(Color(0xFFF1960D), R.drawable.clapreaction_icon),
    SMILE(Color(0xFFF1960D), R.drawable.smilereaction_icon),
    ADD_REACTION(Color.Black, R.drawable.add_reaction_icon)
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PostComponentFeed(
    post: PostResponse,
    modifier: Modifier = Modifier
) {
    var showReactions by remember { mutableStateOf(false) }
    var reactionSelected by remember { mutableStateOf(Reaction.ADD_REACTION) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Transparent)
            .padding(horizontal = 5.dp)
            .padding(bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(35.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.profile_icon),
                contentDescription = "Ícone de perfil não clicável",
                modifier = Modifier
                    .size(45.dp)
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

        Box(
            modifier = Modifier
                .fillMaxWidth(),
        ) {

            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                if (showReactions) {
                    Icon(
                        painter = painterResource(id = Reaction.LIKE.resource),
                        contentDescription = "Ícone clicável para adicionar reação à publicação.",
                        tint = Reaction.LIKE.color,
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                reactionSelected =
                                    if (reactionSelected == Reaction.LIKE) Reaction.ADD_REACTION
                                    else Reaction.LIKE
                                showReactions = false
                            }
                    )

                    Icon(
                        painter = painterResource(id = Reaction.HEART.resource),
                        contentDescription = "Ícone clicável para adicionar reação à publicação.",
                        tint = Reaction.HEART.color,
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                reactionSelected =
                                    if (reactionSelected == Reaction.HEART) Reaction.ADD_REACTION
                                    else Reaction.HEART
                                showReactions = false
                            }
                    )

                    Icon(
                        painter = painterResource(id = Reaction.CLAPS.resource),
                        contentDescription = "Ícone clicável para adicionar reação à publicação.",
                        tint = Reaction.CLAPS.color,
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                reactionSelected =
                                    if (reactionSelected == Reaction.CLAPS) Reaction.ADD_REACTION
                                    else Reaction.CLAPS
                                showReactions = false
                            }
                    )

                    Icon(
                        painter = painterResource(id = Reaction.SMILE.resource),
                        contentDescription = "Ícone clicável para adicionar reação à publicação.",
                        tint = Reaction.SMILE.color,
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                reactionSelected =
                                    if (reactionSelected == Reaction.SMILE) Reaction.ADD_REACTION
                                    else Reaction.SMILE
                                showReactions = false
                            }
                    )
                } else {
                    Icon(
                        painter = painterResource(id = reactionSelected.resource),
                        contentDescription = "Ícone clicável para adicionar reação à publicação.",
                        tint = reactionSelected.color,
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                showReactions = true
                            }
                    )
                }
            }



            Row(
                modifier = Modifier
                    .align(Alignment.BottomEnd),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.message_icon),
                    contentDescription = "Ícone não clicável",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(25.dp)
                )

                Icon(
                    painter = painterResource(id = R.drawable.send_icon),
                    contentDescription = "Ícone não clicável.",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(25.dp)
                )

                Icon(
                    painter = painterResource(id = R.drawable.save_icon),
                    contentDescription = "Ícone não clicável.",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(25.dp)
                )
            }
        }
    }
}