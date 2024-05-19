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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.journey.R
import com.example.journey.screens.Routes

@Composable
fun CustomBottomAppBar(
    navController: NavHostController
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentPage = navBackStackEntry?.destination?.route

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
                .background(
                    color = if (currentPage == Routes.PostsList.route) Color(0xFFEB0049) else Color(0xFFA0A4A3),
                    shape = CircleShape
                )
                .clickable {
                    navController.navigate(Routes.PostsList.route)
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
                .background(
                    color = if (currentPage == Routes.JourneysList.route) Color(0xFFEB0049) else Color(0xFFA0A4A3),
                    shape = CircleShape
                )
                .clickable {
                    navController.navigate(Routes.JourneysList.route)
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
                .background(
                    color = if (currentPage == Routes.Profile.route) Color(0xFFEB0049) else Color(0xFFA0A4A3),
                    shape = CircleShape
                )
                .clickable {
                    navController.navigate(Routes.Profile.route)
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
                .background(
                    color = if (currentPage == Routes.Ranking.route) Color(0xFFEB0049) else Color(0xFFA0A4A3),
                    shape = CircleShape
                )
                .clickable {
                    navController.navigate(Routes.Ranking.route)
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
                .background(
                    color = if (currentPage == Routes.Creation.route) Color(0xFF1448B8) else Color(0xFFA0A4A3),
                    shape = RoundedCornerShape(20.dp)
                )
                .clickable {
                    navController.navigate(Routes.Creation.route)
                }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(30.dp)
            )
        }
    }
}