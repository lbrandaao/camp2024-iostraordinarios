package com.example.journey.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.journey.components.utils.JourneyComponent
import com.example.journey.ui.theme.Poppins

@Composable
fun JourneysListScreen(paddingValues: PaddingValues) {
    val journey1TagsList = listOf(
        "Aprendizado contínuo",
        "Diálogo",
        "Compartilhar"
    )

    val journey2TagsList = listOf(
        "Cuidar",
        "Desenvolvimento pessoal",
        "Comportamento"
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp
            ),
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        item {
            Text(
                text = "Comece sua jornada por aqui: ",
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                lineHeight = 37.sp,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = paddingValues.calculateTopPadding() + 16.dp)

            )
        }

        item {
            JourneyComponent(
                title = "Promova um momento de mentoria reversa.",
                publisherName = "Ana Carolina M.",
                tagsList = journey1TagsList,
                superpowerText = "A Fabulosa Flecha da Agilidade",
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            )
        }


        item {
            JourneyComponent(
                title = "Participe do Webinar da Zenklub sobre Saúde Mental",
                publisherName = "Mônica Araújo",
                tagsList = journey2TagsList,
                superpowerText = "O Impenetrável Escudo do Cuidado",
                onClick = {},
                modifier = Modifier.fillMaxWidth().padding(bottom = paddingValues.calculateBottomPadding()+10.dp)
            )
        }
    }


}


@Preview(showBackground = true)
@Composable
fun JourneysListScreenPreview() {
    JourneysListScreen(paddingValues = PaddingValues())
}