package com.example.journey.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.journey.components.JourneyDropDownMenu
import com.example.journey.ui.theme.Poppins
import com.example.journey.ui.theme.PrimaryBackgroundColor

@Composable
fun FirstAccessScreen() {
    val dropDownItemsTextList = listOf(
        "O impenetrável escudo do cuidado",
        "A varinha mágica da transformação",
        "O poder infinito da mente",
        "O incrível cristal do extraordinário",
        "As maravilhosas asas para inovar",
        "A fabulosa flecha da agilidade",
        "O indestrutível laço da evolução"
    )

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = PrimaryBackgroundColor
    ) {
        Column(
            modifier = Modifier
                .padding(top = 46.dp, start = 17.dp, end = 17.dp)
        ) {
            JourneyDropDownMenu(
                label = "Selecione o seu superpoder",
                placeholder = "Superpoder",
                dropDownItemsTextList = dropDownItemsTextList
            )

            Text(
                text = "Como você se engaja com os valores de cultura nas suas atividades diárias?",
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 22.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FirstAccessScreenPreview() {
    FirstAccessScreen()
}