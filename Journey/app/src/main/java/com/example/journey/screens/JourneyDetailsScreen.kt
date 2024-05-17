package com.example.journey.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.journey.R
import com.example.journey.data.models.Journey
import com.example.journey.ui.theme.Poppins
import com.example.journey.ui.theme.PrimaryBackgroundColor

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun JourneyDetailsScreen(
    journey: Journey?,
    onBackClick: () -> Unit,
    onAcceptClick: () -> Unit
) {
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = PrimaryBackgroundColor
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {

            item {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 35.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Ícone clicável para voltar à tela anterior.",
                        tint = Color(0xFF353F38),
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                onBackClick.invoke()
                            }
                    )

                    Text(
                        text = "Detalhes da Jornada",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp,
                        color = Color.Black
                    )

                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Ícone sem funcionalidade.",
                        tint = Color(0xFF353F38),
                        modifier = Modifier
                            .size(25.dp)
                    )
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    journey?.let {
                        Text(
                            text = it.title,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            color = Color.Black
                        )

                        Text(
                            text = it.description,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(top = 25.dp)
                        )

                        FlowRow(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalArrangement = Arrangement.spacedBy((-5).dp)
                        ) {
                            journey.tags.forEach {
                                AssistChip(
                                    onClick = {},
                                    label = {
                                        Text(
                                            text = it,
                                            fontFamily = Poppins,
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 12.sp,
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
                                        text = journey.superpower,
                                        fontFamily = Poppins,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 12.sp,
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

                        Row (
                            modifier = Modifier
                                .padding(top = 15.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(
                                text = "Ganhe:",
                                fontFamily = Poppins,
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp,
                                color = Color.Black
                            )

                            Row (
                                modifier = Modifier
                                    .background(
                                        color = Color(0xFF185AE6),
                                        shape = RoundedCornerShape(25.dp)
                                    )
                                    .padding(horizontal = 10.dp, vertical = 5.dp),
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ){
                                Text(
                                    text = "+ ${journey.nuts}",
                                    fontFamily = Poppins,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    color = Color.White
                                )

                                Icon(
                                    painter = painterResource(id = R.drawable.nut_icon),
                                    contentDescription = "Ícone de noz sem funcionalidade.",
                                    tint = Color.White,
                                    modifier = Modifier
                                        .size(25.dp)
                                )
                            }
                        }

                        OutlinedButton(
                            onClick = {
                                onAcceptClick.invoke()
                            },
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = Color(0xFFEB0049)
                            ),
                            border = null,
                            shape = RoundedCornerShape(24.dp),
                            modifier = Modifier
                                .padding(top = 40.dp, bottom = 20.dp)
                                .size(width = 176.dp, height = 40.dp)
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Text(
                                text = "Aceitar",
                                fontFamily = Poppins,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                                color = Color.White
                            )
                        }
                    } ?: Text(
                        text = "Nenhuma jornada selecionada.",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color.Black
                    )
                }
            }

        }


    }
}


val journey = Journey(
    title = "Promova um momento de mentoria reversa.",
    description = "Você já pensou no que a nova geração pode ensinar para líderes que já " +
            "estão no mercado há anos? \n" +
            "\n" +
            "Essa missão propõe uma inversão de papéis: desafiamos você, líder experiente, " +
            "a abrir-se para novas perspectivas e aprendizados com seus colaboradores " +
            "mais jovens e entender quais os impactos positivos eles podem trazer para o " +
            "seu dia-a-dia e você, que acabou de entrar no mercado, comece a pensar em como " +
            "sua carreira ainda em construção pode nos ajudar a crescer!",
    publisher = "Ana Carolina M.",
    superpower = "A Fabulosa Flecha da Agilidade",
    tags = listOf("Aprendizado contínuo", "Diálogo", "Compartilhar"),
    nuts = 200
)
@Preview
@Composable
fun JourneyDetailsScreenPreview() {
    JourneyDetailsScreen(journey = journey, onBackClick = {  }) {

    }
}