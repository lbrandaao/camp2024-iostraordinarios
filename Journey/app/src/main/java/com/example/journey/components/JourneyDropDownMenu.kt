package com.example.journey.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.journey.ui.theme.Poppins

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JourneyDropDownMenu(
    label: String,
    placeholder: String,
    dropDownItemsTextList: List<String>,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }
    var textFieldValue by remember { mutableStateOf("") }

    Column (
        modifier = modifier
    ){
        Text(
            text = label,
            fontFamily = Poppins,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )

        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = it }
        ) {
            OutlinedTextField(
                value = textFieldValue,
                onValueChange = { },
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedIndicatorColor = Color(0xFFE0E0E0),
                    focusedIndicatorColor = Color(0xFFE0E0E0),
                    unfocusedTrailingIconColor = Color(0xFF828282),
                    focusedTrailingIconColor = Color(0xFF828282),
                    unfocusedTextColor = Color(0xFF828282),
                    focusedTextColor = Color.Black,
                    unfocusedPlaceholderColor = Color(0xFF828282),
                    focusedPlaceholderColor = Color(0xFF828282),
                ),
                placeholder = {
                    Text(
                        text = placeholder,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp
                    )
                },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .height(55.dp)
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
            ) {
                dropDownItemsTextList.forEach {
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = it,
                                fontFamily = Poppins,
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                        },
                        onClick = {
                            textFieldValue = it
                            isExpanded = false
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp, start = 2.dp, end = 2.dp)
                            .border(1.dp, Color(0xFFE0E0E0))
                            .background(color = Color.Blue)
                    )
                }
            }
        }
    }


}