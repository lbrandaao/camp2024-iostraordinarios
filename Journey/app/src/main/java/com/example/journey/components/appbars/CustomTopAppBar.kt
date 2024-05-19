package com.example.journey.components.appbars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.journey.R
import com.example.journey.ui.theme.Poppins
import com.example.journey.viewModels.UserViewModel

@Composable
fun CustomTopAppBar(
    navControllerNoAppBars: NavHostController,
    navControllerWithAppBars: NavHostController,
    userViewModel: UserViewModel
) {
    if (userViewModel.getAuthenticatedUser() == null) userViewModel.setAuthenticatedUser()

    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .background(color = Color.Transparent)
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { },
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color.Black
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.profile_icon),
                contentDescription = "Ícone clicável que navega para página de perfil."
            )
        }

        if (userViewModel.isReady()) {
            Row(
                modifier = Modifier
                    .size(width = 85.dp, height = 40.dp)
                    .background(
                        color = Color.Black,
                        shape = CircleShape
                    )
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = userViewModel.getAuthenticatedUser()?.nuts.toString(),
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(top = 3.dp)
                )

                Icon(
                    painter = painterResource(id = R.drawable.nut_icon),
                    contentDescription = "Ícone não clicável de uma noz.",
                    tint = Color.White,
                    modifier = Modifier
                        .size(30.dp)
                )
            }
        }
    }

}