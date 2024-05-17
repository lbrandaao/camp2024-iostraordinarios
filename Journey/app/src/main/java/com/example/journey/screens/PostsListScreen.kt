package com.example.journey.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PostsListScreen(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = paddingValues.calculateTopPadding() + 60.dp)
    ) {
        Text(
            text = "Últimos destaques",
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp)
        )
    }


}