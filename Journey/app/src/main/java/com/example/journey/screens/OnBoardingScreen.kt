package com.example.journey.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.journey.R
import com.example.journey.ui.theme.Poppins
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen() {
    val pageTextList = listOf(
        "Boas Vindas ao Ioasys Journey!\n" +
                "\n" +
                "Aqui, você encontrará maneiras de vivenciar e promover os valores que são " +
                "fundamentais para nós, tendo acesso a práticas diversas.",
        "Compartilhe suas experiências, ideias e aprendizados, promova uma troca constante entre " +
                "todos os colaboradores, e receba um retorno por todo esse empenho!",
        "Cada conquista representa o seu comprometimento em tornar o nosso trabalho ainda " +
                "melhor.\n" +
                "\n" +
                "Vamos começar a transformar juntos?"
    )

    val pageArtList = listOf(
        R.drawable.teamgoals_art,
        R.drawable.goodteam_art,
        R.drawable.creativeteam_art
    )

    val pagerState = rememberPagerState(pageCount = pageTextList.size)

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.Black
    ) {
        Column(
            modifier = Modifier
                .padding(top = 45.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            PageIndicator(
                pageCount = pageTextList.size,
                currentPage = pagerState.currentPage,
                modifier = Modifier.padding(horizontal = 25.dp)
            )

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(top = 45.dp)
            ) { currentPage ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.8f),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = pageTextList[currentPage],
                        fontFamily = Poppins,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 26.sp,
                        color = Color.White,
                        lineHeight = 32.sp,
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                    )

                    Image(painter = painterResource(id = pageArtList[currentPage]),
                        contentDescription = "Arte não clicável",
                        modifier = Modifier
                            .align(Alignment.End)
                            .size(330.dp)
                        )
                }
            }


        }
    }
}

@Composable
fun PageIndicator(pageCount: Int, currentPage: Int, modifier: Modifier = Modifier) {
    Row (
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        repeat(pageCount) {
            Indicator(isSelected = it == currentPage)
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean) {
    val boxColor = animateColorAsState(
        targetValue = if (isSelected) Color(0xFFD71954) else Color(0xFFD9D9D9),
        label = "Box Color"
    )

    Box (
        modifier = Modifier
            .size(width = 94.dp, height = 16.dp)
            .background(color = boxColor.value, shape = RoundedCornerShape(32.dp))
    )
}

@Preview(showBackground = true)
@Composable
fun OnBoardingScreenPreview() {
    OnBoardingScreen()
}