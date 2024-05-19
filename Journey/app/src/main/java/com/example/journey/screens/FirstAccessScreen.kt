package com.example.journey.screens

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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.journey.MainActivity
import com.example.journey.components.textfields.CustomDropDownMenuSuperpowers
import com.example.journey.ui.theme.Poppins
import com.example.journey.ui.theme.PrimaryBackgroundColor
import com.example.journey.viewModels.SuperpowerViewModel
import com.example.journey.viewModels.TagViewModel
import com.example.journey.viewModels.UserViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FirstAccessScreen(
    context: MainActivity,
    userViewModel: UserViewModel,
    superpowerViewModel: SuperpowerViewModel,
    tagViewModel: TagViewModel,
    onRegistrationConfirm: () -> Unit
) {

    if (
        superpowerViewModel.listSuperpowers() == null ||
        tagViewModel.listTags() == null
        ) {
        superpowerViewModel.loadSuperpowers()
        tagViewModel.loadTags()
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = PrimaryBackgroundColor
    ) {

        if (userViewModel.isReady() &&
            superpowerViewModel.isReady() &&
            tagViewModel.isReady()) {
            val dropDownItemsTextList =
                superpowerViewModel.listSuperpowers()?.map { it.name }?: listOf()
            var dropDownValue by remember { mutableStateOf("") }

            val chipsTextList = tagViewModel.listTags()?.map { it.name }?: listOf()
            val selectedStringTagsList = mutableListOf<String>()

            Column(
                modifier = Modifier
                    .padding(top = 60.dp, start = 17.dp, end = 17.dp)
            ) {
                CustomDropDownMenuSuperpowers(
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
                                superpowerViewModel.listSuperpowers()
                                    ?.find { it.name == dropDownValue }?.id
                                    ?: 1
                            val selectedIdTagsList = selectedStringTagsList
                                .map { tagString ->
                                    tagViewModel.listTags()
                                        ?.find { it.name == tagString }?.id
                                        ?: 1
                                }

                            userViewModel.createUser(
                                selectedSuperpowerId,
                                selectedIdTagsList,
                                context,
                                onRegistrationConfirm)
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
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(120.dp),
                    color = Color.Black,
                    strokeWidth = 8.dp
                )
            }
        }
    }
}