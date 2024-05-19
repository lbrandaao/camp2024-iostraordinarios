package com.example.journey.components.textfields

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
fun CustomDropDownMenuCreation(
    value: String,
    onItemClick: (String) -> Unit,
    placeholder: String,
    dropDownItemsTextList: List<String>,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column (
        modifier = modifier
    ){
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = it }
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = { },
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedTrailingIconColor = Color.Black,
                    focusedTrailingIconColor = Color(0xFF688FE9),
                    unfocusedTextColor = Color.Black,
                    focusedTextColor = Color(0xFF688FE9),
                    unfocusedPlaceholderColor = Color.Black,
                    focusedPlaceholderColor = Color(0xFF688FE9),
                    cursorColor = Color.Red
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
                    .height(52.dp)
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false },
                modifier = Modifier
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
                            onItemClick(it)
                            isExpanded = false
                        }
                    )
                }
            }
        }
    }


}