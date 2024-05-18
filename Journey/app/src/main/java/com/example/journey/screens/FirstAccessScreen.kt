package com.example.journey.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.example.journey.MainActivity
import com.example.journey.components.textfields.CustomDropDownMenu
import com.example.journey.data.repository.SuperpowerRepository
import com.example.journey.data.repository.TagRepository
import com.example.journey.ui.theme.Poppins
import com.example.journey.ui.theme.PrimaryBackgroundColor
import com.example.journey.viewModels.UserViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FirstAccessScreen(
    context: MainActivity,
    userViewModel: UserViewModel,
    onFinishButtonClick: () -> Unit
) {
    val superpowerRepository = SuperpowerRepository()
    val tagRepository = TagRepository()

    val superpowersList = superpowerRepository.listSuperpowers()
    val dropDownItemsTextList = superpowersList.map { it.name }
    var dropDownValue by remember { mutableStateOf("") }

    val tagsList = tagRepository.listTags()
    val chipsTextList = tagsList.map { it.name }
    val selectedStringTagsList = mutableListOf<String>()

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = PrimaryBackgroundColor
    ) {
        Column(
            modifier = Modifier
                .padding(top = 60.dp, start = 17.dp, end = 17.dp)
        ) {
            CustomDropDownMenu(
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
                                selectedStringTagsList -= chipText
                            else
                                selectedStringTagsList += chipText
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
                onClick = {
                    if (dropDownValue.isNotBlank() &&
                        selectedStringTagsList.isNotEmpty()
                    ) {
                        val selectedSuperpowerId =
                            superpowersList.find { it.name == dropDownValue }?.id ?: 0
                        val selectedIdTagsList = selectedStringTagsList
                            .map { tagString ->
                                tagsList.find { it.name == tagString }?.id ?: 0
                            }

                        val userCreated =
                            userViewModel.createUser(selectedSuperpowerId, selectedIdTagsList)
                        if (userCreated) {
                            onFinishButtonClick.invoke()
                        } else Toast.makeText(
                            context,
                            "Algo deu errado.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                },
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color(0xFF306BE9)
                ),
                modifier = Modifier
                    .padding(top = 35.dp)
                    .align(Alignment.CenterHorizontally)
                    .size(width = 176.dp, height = 40.dp),
                border = null
            ) {
                Text(
                    text = "Finalizar",
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
fun FirstAccessScreenPreview() {
    FirstAccessScreen(
        context = MainActivity(),
        userViewModel = UserViewModel(),
        onFinishButtonClick = {}
    )
}