package com.example.journey.components.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.journey.R
import com.example.journey.data.models.Post
import com.example.journey.ui.theme.Poppins

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PostComponent (
    post: Post,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .width(300.dp)
            .then(modifier)
            .background(
                color = Color(0xFFD1DEFA),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(vertical = 24.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
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
                .height(170.dp),
            overflow = TextOverflow.Ellipsis
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
        }
    }
}