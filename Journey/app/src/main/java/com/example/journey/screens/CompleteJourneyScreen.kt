package com.example.journey.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.journey.R
import com.example.journey.components.textfields.CustomDatePicker
import com.example.journey.components.textfields.CustomTextField
import com.example.journey.ui.theme.Poppins
import com.example.journey.ui.theme.PrimaryBackgroundColor

@Composable
fun CompleteJourneyScreen(
    onBackClick: () -> Unit,
    onSendClick: () -> Unit
) {
    var invitedFriendName by remember {
        mutableStateOf("")
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = PrimaryBackgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 35.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
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
                    text = "Concluir Jornada",
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


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .padding(top = 70.dp),
                verticalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                CustomDatePicker(
                    label = "Data de conclusão da Jornada",
                    placeholder = "dd/mm/aaaa"
                )

                CustomTextField(
                    value = invitedFriendName,
                    onValueChange = {
                        invitedFriendName = it
                    },
                    label = "Convidar amigo",
                    placeholder = "Pesquisar pessoas",
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Ícone não clicável",
                            tint = Color(0xFF828282),
                            modifier = Modifier
                                .size(32.dp)
                        )
                    }
                )

                CustomTextField(
                    value = "",
                    onValueChange = {},
                    readOnly = true,
                    label = "Anexar comprovante",
                    placeholder = ".pdf .jpeg.",
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.upload_icon),
                            contentDescription = "Ícone não clicável",
                            tint = Color(0xFF828282),
                            modifier = Modifier
                                .size(32.dp)
                        )
                    }
                )
            }

            OutlinedButton(
                onClick = {
                    onSendClick.invoke()
                },
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color(0xFFEB0049)
                ),
                border = null,
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .padding(top = 80.dp, bottom = 20.dp)
                    .size(width = 176.dp, height = 40.dp)
            ) {
                Text(
                    text = "Enviar",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Color.White
                )
            }

        }
    }
}


@Preview
@Composable
fun CompleteJourneyScreenPreview() {
    CompleteJourneyScreen(
        onBackClick = {},
        onSendClick = {}
    )
}