package com.example.journey.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.journey.R
import com.example.journey.components.textfields.JourneyTextField
import com.example.journey.ui.theme.Poppins
import com.example.journey.ui.theme.PrimaryBackgroundColor

@Composable
fun LoginScreen(
    onConfirmButtonClick: () -> Unit,
    onRegistrationClick: () -> Unit
) {
    var emailValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = PrimaryBackgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 45.dp, end = 45.dp, top = 75.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_journey),
                contentDescription = "Logo não clicável",
                modifier = Modifier.size(width = 111.dp, height = 118.dp)
            )

            Text(
                text = "Fazer login",
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp),
                color = Color.Black
            )

            JourneyTextField(
                value = emailValue,
                onValueChange = {
                    emailValue = it
                },
                label = "Email",
                placeholder = "exemplo@ioasys.com",
                modifier = Modifier
                    .padding(top = 35.dp),
            )

            JourneyTextField(
                value = passwordValue,
                onValueChange = {
                    passwordValue = it
                },
                label = "Senha",
                modifier = Modifier
                    .padding(top = 35.dp),
                visualTransformation = PasswordVisualTransformation()
            )

            Text(
                text = "Esqueci a senha",
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(12.dp),
                textDecoration = TextDecoration.Underline,
                fontFamily = Poppins,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                color = Color(0xFF626262)
            )

            Text(
                text = "Não tenho cadastro",
                fontFamily = Poppins,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = Color(0xFF7D8281),
                modifier = Modifier
                    .padding(top = 54.dp)
                    .clickable {
                        onRegistrationClick.invoke()
                    }
            )

            OutlinedButton(
                onClick = { onConfirmButtonClick.invoke() },
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color(0xFF306BE9)
                ),
                border = null,
                modifier = Modifier
                    .padding(top = 50.dp)
                    .size(width = 176.dp, height = 40.dp)

            ) {
                Text(
                    text = "Continuar",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Color.White
                )
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        onConfirmButtonClick = {},
        onRegistrationClick = {}
    )
}