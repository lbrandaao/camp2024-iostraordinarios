package com.example.journey.components.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.journey.ui.theme.Poppins

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun JourneyComponent(
    title: String,
    publisherName: String,
    tagsList: List<String>,
    superpowerText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(
                color = Color(0xFFD1DEFA),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(vertical = 24.dp, horizontal = 16.dp)
    ) {
        Text(
            text = title,
            fontFamily = Poppins,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            color = Color.Black
        )
        Text(
            text = "Publicado por: $publisherName",
            fontFamily = Poppins,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            color = Color.Black
        )

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalArrangement = Arrangement.spacedBy((-5).dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
        ) {


            tagsList.forEach {
                AssistChip(
                    onClick = {},
                    label = {
                        Text(
                            text = it,
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
                        text = superpowerText,
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

        Text(
            text = "Ver detalhes",
            fontFamily = Poppins,
            fontWeight = FontWeight.Medium,
            fontSize = 10.sp,
            textDecoration = TextDecoration.Underline,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.End)
                .clickable {
                    onClick.invoke()
                }
        )
    }
}