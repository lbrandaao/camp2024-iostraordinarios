package com.example.journey.components.appbars

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.journey.R

@Composable
fun CustomBottomAppBar(
    navController: NavHostController
){
    var selected by remember { mutableIntStateOf(1) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .padding(horizontal = 18.dp)
            .background(
                color = Color(0xFFE4E5E5),
                shape = RoundedCornerShape(50.dp)
            )
            .padding(horizontal = 32.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(50.dp)
                .background(color = if (selected==0) Color(0xFFEB0049) else Color(0xFFA0A4A3), shape = CircleShape)
                .clickable {
                    selected = 0
                    navController.navigate("postslist")
                }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.document_icon),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Box(
            modifier = Modifier
                .size(50.dp)
                .background(color = if (selected==1) Color(0xFFEB0049) else Color(0xFFA0A4A3), shape = CircleShape)
                .clickable {
                    selected = 1
                    navController.navigate("journeyslist")
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.tasks_icon),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Box(
            modifier = Modifier
                .size(50.dp)
                .background(color = if (selected==2) Color(0xFFEB0049) else Color(0xFFA0A4A3), shape = CircleShape)
                .clickable {
                    selected = 2
                }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.profile_icon),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Box(
            modifier = Modifier
                .size(50.dp)
                .background(color = if (selected==3) Color(0xFFEB0049) else Color(0xFFA0A4A3), shape = CircleShape)
                .clickable {
                    selected = 3
                }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.trophy_icon),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Box(
            modifier = Modifier
                .size(52.dp)
                .background(color = if (selected==4) Color(0xFFEB0049) else Color(0xFFA0A4A3),
                    shape = RoundedCornerShape(20.dp)
                )
                .clickable {
                    selected = 4
                }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.align(Alignment.Center).size(30.dp)
            )
        }


    }
}