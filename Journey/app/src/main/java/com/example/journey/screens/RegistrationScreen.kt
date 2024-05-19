package com.example.journey.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.journey.MainActivity
import com.example.journey.R
import com.example.journey.components.textfields.CustomDropDownMenuSuperpowers
import com.example.journey.components.textfields.CustomTextField
import com.example.journey.ui.theme.Poppins
import com.example.journey.ui.theme.PrimaryBackgroundColor
import com.example.journey.viewModels.UserViewModel

@Composable
fun RegistrationScreen(
    context: MainActivity,
    userViewModel: UserViewModel,
    onBackButtonClick: () -> Unit,
    onContinueButtonClick: () -> Unit
) {
    var nameValue by remember { mutableStateOf("") }
    var emailValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }
    var confirmPasswordValue by remember { mutableStateOf("") }

    val dropDownItemsTextList = listOf(
        "Agilista",
        "Cientista de Dados",
        "Desenvolvedor Backend",
        "Desenvolvedor Frontend",
        "Desenvolvedor Mobile",
        "Designer",
        "QA"
    )

    var occupationValue by remember { mutableStateOf("") }

    var isLeader by remember { mutableStateOf<Boolean?>(null) }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = PrimaryBackgroundColor
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(top = 15.dp, start = 40.dp, end = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Image(
                    painter = painterResource(id = R.drawable.logo_journey),
                    contentDescription = "Logo não clicável",
                    modifier = Modifier
                        .size(115.dp)
                )
            }

            item {
                Text(
                    text = "Criar conta",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 35.dp)
                )
            }

            item {
                CustomTextField(
                    value = nameValue,
                    onValueChange = {
                        nameValue = it
                    },
                    label = "Nome Completo",
                    placeholder = "Exemplo",
                    modifier = Modifier
                        .padding(top = 30.dp)
                )
            }

            item {
                CustomTextField(
                    value = emailValue,
                    onValueChange = {
                        emailValue = it
                    },
                    label = "Email",
                    placeholder = "exemplo@ioasys.com",
                    modifier = Modifier
                        .padding(top = 24.dp)
                )
            }

            item {
                CustomTextField(
                    value = passwordValue,
                    onValueChange = {
                        passwordValue = it
                    },
                    label = "Senha",
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .padding(top = 24.dp)
                )
            }

            item {
                CustomTextField(
                    value = confirmPasswordValue,
                    onValueChange = {
                        confirmPasswordValue = it
                    },
                    label = "Confirmar senha",
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .padding(top = 24.dp)
                )
            }

            item {
                CustomDropDownMenuSuperpowers(
                    value = occupationValue,
                    onItemClick = {
                        occupationValue = it
                    },
                    label = "Qual é seu cargo?",
                    placeholder = "Agilista",
                    dropDownItemsTextList = dropDownItemsTextList,
                    modifier = Modifier
                        .padding(top = 24.dp)
                )
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                ) {
                    Text(
                        text = "Você é uma liderança na ioasys?",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 0.dp),
                        horizontalArrangement = Arrangement.spacedBy(25.dp)
                    ) {
                        AssistChip(
                            onClick = {
                                isLeader?.let {
                                    isLeader = true
                                } ?: run {
                                    isLeader = true
                                }
                            },
                            label = {
                                Text(
                                    text = "Sim",
                                    fontFamily = Poppins,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 10.sp,
                                    color = isLeader?.let {
                                        if (it) Color.White else Color(0xFF185AE6)
                                    } ?: Color(0xFF185AE6)
                                )
                            },
                            border = AssistChipDefaults.assistChipBorder(
                                borderColor = Color(0xFF185AE6),
                                borderWidth = 1.dp
                            ),
                            shape = RoundedCornerShape(100.dp),
                            colors = AssistChipDefaults.assistChipColors(
                                containerColor = isLeader?.let {
                                    if (it) Color(0xFF185AE6) else Color.Transparent
                                } ?: Color.Transparent
                            )
                        )

                        AssistChip(
                            onClick = {
                                isLeader?.let {
                                    isLeader = false
                                } ?: run {
                                    isLeader = false
                                }
                            },
                            label = {
                                Text(
                                    text = "Não",
                                    fontFamily = Poppins,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 10.sp,
                                    color = isLeader?.let {
                                        if (it) Color(0xFF185AE6) else Color.White
                                    } ?: Color(0xFF185AE6)
                                )
                            },
                            border = AssistChipDefaults.assistChipBorder(
                                borderColor = Color(0xFF185AE6),
                                borderWidth = 1.dp
                            ),
                            shape = RoundedCornerShape(100.dp),
                            colors = AssistChipDefaults.assistChipColors(
                                containerColor = isLeader?.let {
                                    if (it) Color.Transparent else Color(0xFF185AE6)
                                } ?: Color.Transparent
                            )
                        )
                    }
                }
            }

            item {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 40.dp, top = 30.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    OutlinedButton(
                        onClick = {
                            onBackButtonClick.invoke()
                        },
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.Transparent
                        ),
                        border = BorderStroke(1.dp, Color(0xFF759CF0)),
                        shape = RoundedCornerShape(24.dp),
                        modifier = Modifier
                            .size(width = 160.dp, height = 40.dp)
                    ) {
                        Text(
                            text = "Voltar",
                            fontFamily = Poppins,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = Color(0xFF759CF0)
                        )
                    }

                    OutlinedButton(
                        onClick = {
                            if (
                                nameValue.isNotBlank() &&
                                emailValue.isNotBlank() &&
                                passwordValue.isNotBlank() &&
                                confirmPasswordValue.isNotBlank() &&
                                passwordValue == confirmPasswordValue &&
                                occupationValue.isNotBlank() &&
                                isLeader != null
                            ) {
                                userViewModel.saveNewUserData(
                                    nameValue,
                                    emailValue,
                                    passwordValue,
                                    occupationValue,
                                    if(isLeader!!) "leader" else "user"
                                )
                                onContinueButtonClick.invoke()
                            } else {
                                Toast.makeText(
                                    context,
                                    "Preencha todos os campos corretamente.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color(0xFF306BE9)
                        ),
                        border = BorderStroke(1.dp, Color(0xFF306BE9)),
                        shape = RoundedCornerShape(24.dp),
                        modifier = Modifier
                            .size(width = 160.dp, height = 40.dp)
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
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationScreenPreview() {
    RegistrationScreen(
        context = MainActivity(),
        userViewModel = UserViewModel(),
        onBackButtonClick = {},
        onContinueButtonClick = {}
    )
}