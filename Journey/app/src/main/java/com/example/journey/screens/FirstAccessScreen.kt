package com.example.journey.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.journey.components.JourneyDropDownMenu
import com.example.journey.ui.theme.Poppins
import com.example.journey.ui.theme.PrimaryBackgroundColor

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FirstAccessScreen(onFinishButtonClick: () -> Unit) {
    val dropDownItemsTextList = listOf(
        "O impenetrável escudo do cuidado",
        "A varinha mágica da transformação",
        "O poder infinito da mente",
        "O incrível cristal do extraordinário",
        "As maravilhosas asas para inovar",
        "A fabulosa flecha da agilidade",
        "O indestrutível laço da evolução"
    )

    var dropDownValue by remember { mutableStateOf("") }

    val chipsTextList = listOf(
        "Transformar",
        "Cuidar",
        "Diversificar",
        "Respeitar",
        "Produzir bem",
        "Criatividade",
        "Inovação",
        "Excelência",
        "Reconhecimento",
        "Surpreender",
        "Novidade",
        "Expansão",
        "Manutenção",
        "Agilidade",
        "Comportamento",
        "Transparência",
        "Expectativas x Restrições",
        "Desenvolvimento pessoal",
        "Softskills",
        "Aprendizado contínuo",
        "Evolução",
        "Mercado",
        "Constância",
        "Diálogo",
        "Compartilhar"
    )

    val selectedTagsList = mutableListOf<String>()

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = PrimaryBackgroundColor
    ) {
        Column(
            modifier = Modifier
                .padding(top = 60.dp, start = 17.dp, end = 17.dp)
        ) {
            JourneyDropDownMenu(
                value = dropDownValue,
                onItemClick = {
                    dropDownValue = it
                },
                label = "Selecione o seu superpoder",
                placeholder = "Superpoder",
                dropDownItemsTextList = dropDownItemsTextList
            )

            Text(
                text = "Como você se engaja com os valores de cultura nas suas atividades diárias?",
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 30.dp)
            )

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy((-5).dp),
                modifier = Modifier
                    .padding(top = 10.dp)
            ) {
                chipsTextList.forEach { chipText ->
                    var isSelected by remember {
                        mutableStateOf(false)
                    }

                    AssistChip(
                        onClick = {
                            if (isSelected)
                                selectedTagsList -= chipText
                            else
                                selectedTagsList += chipText
                            isSelected = !isSelected
                        },
                        label = {
                            Text(
                                text = chipText,
                                fontFamily = Poppins,
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp,
                                color = if (isSelected) Color.White else Color(0xFF185AE6)
                            )
                        },
                        border = AssistChipDefaults.assistChipBorder(
                            borderColor = Color(0xFF185AE6),
                            borderWidth = 1.dp
                        ),
                        shape = RoundedCornerShape(100.dp),
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor =
                            if (isSelected) Color(0xFF185AE6)
                            else Color.Transparent
                        ),
                        trailingIcon = {
                            if (isSelected)
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Ícone clicável para desselecionar tag",
                                    tint = Color.White
                                )
                        }
                    )
                }
            }

            OutlinedButton(
                onClick = { onFinishButtonClick.invoke() },
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color(0xFFFF3D79)
                ),
                modifier = Modifier
                    .padding(top = 35.dp)
                    .align(Alignment.CenterHorizontally),
                border = null
            ) {
                Text(
                    text = "Finalizar",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 3.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FirstAccessScreenPreview() {
    FirstAccessScreen() {}
}