package com.example.journey.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.example.journey.components.utils.PostComponent
import com.example.journey.data.models.Post
import com.example.journey.data.models.Superpower
import com.example.journey.data.models.Tag
import com.example.journey.data.models.UserResponse
import com.example.journey.ui.theme.Poppins
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.absoluteValue


val lista = listOf(
    Post(
        title = "",
        description = "Recentemente, participei de um projeto que envolveu a reestruturação de " +
                "alguns dos nosso processos e, utilizando princípios de inovação, propusemos uma " +
                "abordagem totalmente nova, focada na personalização e na experiência do cliente.\n" +
                "Apresentamos um plano que não apenas solucionou nosso problema, mas também " +
                "melhorou a satisfação do cliente e aumentou a eficiência operacional. " +
                "Foi gratificante ver como a busca por novas ideias e soluções trouxe resultados " +
                "tangíveis para a empresa.",
        superpowers = listOf(
            Superpower(0, "As maravilhosas asas para voar")
        ),
        tags = listOf(
            Tag(0, "Desenvolvimento pessoal"),
            Tag(0, "Surpreender"),
            Tag(0, "Inovação")
        ),
        creator = UserResponse(
            id = 0,
            fullName = "Amélia dos Santos",
            email = "",
            position = "",
            role = "",
            nuts = 0,
            superpower = Superpower(0, "As maravilhosas asas para voar"),
            tags = listOf(
                Tag(0, "Desenvolvimento pessoal"),
                Tag(0, "Surpreender"),
                Tag(0, "Inovação")
            ),
            bio = "",
            interactionsCount = 0,
            score = 0,
            missionsCompleted = 0
        )

    ),
    Post(
        title = "",
        description = "Recentemente, participei de um projeto que envolveu a reestruturação de " +
                "alguns dos nosso processos e, utilizando princípios de inovação, propusemos uma " +
                "abordagem totalmente nova, focada na personalização e na experiência do cliente.\n" +
                "Apresentamos um plano que não apenas solucionou nosso problema, mas também " +
                "melhorou a satisfação do cliente e aumentou a eficiência operacional. " +
                "Foi gratificante ver como a busca por novas ideias e soluções trouxe resultados " +
                "tangíveis para a empresa.",
        superpowers = listOf(
            Superpower(0, "As maravilhosas asas para voar")
        ),
        tags = listOf(
            Tag(0, "Desenvolvimento pessoal"),
            Tag(0, "Surpreender"),
            Tag(0, "Inovação")
        ),
        creator = UserResponse(
            id = 0,
            fullName = "Amélia dos Santos",
            email = "",
            position = "",
            role = "",
            nuts = 0,
            superpower = Superpower(0, "As maravilhosas asas para voar"),
            tags = listOf(
                Tag(0, "Desenvolvimento pessoal"),
                Tag(0, "Surpreender"),
                Tag(0, "Inovação")
            ),
            bio = "",
            interactionsCount = 0,
            score = 0,
            missionsCompleted = 0
        )

    ),
    Post(
        title = "",
        description = "Recentemente, participei de um projeto que envolveu a reestruturação de " +
                "alguns dos nosso processos e, utilizando princípios de inovação, propusemos uma " +
                "abordagem totalmente nova, focada na personalização e na experiência do cliente.\n" +
                "Apresentamos um plano que não apenas solucionou nosso problema, mas também " +
                "melhorou a satisfação do cliente e aumentou a eficiência operacional. " +
                "Foi gratificante ver como a busca por novas ideias e soluções trouxe resultados " +
                "tangíveis para a empresa.",
        superpowers = listOf(
            Superpower(0, "As maravilhosas asas para voar")
        ),
        tags = listOf(
            Tag(0, "Desenvolvimento pessoal"),
            Tag(0, "Surpreender"),
            Tag(0, "Inovação")
        ),
        creator = UserResponse(
            id = 0,
            fullName = "Amélia dos Santos",
            email = "",
            position = "",
            role = "",
            nuts = 0,
            superpower = Superpower(0, "As maravilhosas asas para voar"),
            tags = listOf(
                Tag(0, "Desenvolvimento pessoal"),
                Tag(0, "Surpreender"),
                Tag(0, "Inovação")
            ),
            bio = "",
            interactionsCount = 0,
            score = 0,
            missionsCompleted = 0
        )

    ),
    Post(
        title = "",
        description = "Recentemente, participei de um projeto que envolveu a reestruturação de " +
                "alguns dos nosso processos e, utilizando princípios de inovação, propusemos uma " +
                "abordagem totalmente nova, focada na personalização e na experiência do cliente.\n" +
                "Apresentamos um plano que não apenas solucionou nosso problema, mas também " +
                "melhorou a satisfação do cliente e aumentou a eficiência operacional. " +
                "Foi gratificante ver como a busca por novas ideias e soluções trouxe resultados " +
                "tangíveis para a empresa.",
        superpowers = listOf(
            Superpower(0, "As maravilhosas asas para voar")
        ),
        tags = listOf(
            Tag(0, "Desenvolvimento pessoal"),
            Tag(0, "Surpreender"),
            Tag(0, "Inovação")
        ),
        creator = UserResponse(
            id = 0,
            fullName = "Amélia dos Santos",
            email = "",
            position = "",
            role = "",
            nuts = 0,
            superpower = Superpower(0, "As maravilhosas asas para voar"),
            tags = listOf(
                Tag(0, "Desenvolvimento pessoal"),
                Tag(0, "Surpreender"),
                Tag(0, "Inovação")
            ),
            bio = "",
            interactionsCount = 0,
            score = 0,
            missionsCompleted = 0
        )

    )
)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PostsListScreen(
    paddingValues: PaddingValues,
    onSeeMoreButtonClick: () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = lista.size)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = paddingValues.calculateTopPadding() + 60.dp,
                bottom = paddingValues.calculateBottomPadding() + 30.dp
            ),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Últimos destaques",
            fontWeight = FontWeight.SemiBold,
            fontSize = 32.sp,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp)
        )

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth(),
            itemSpacing = 0.dp
        ) { page ->
            lista.forEach {
                PostComponent(
                    post = it,
                    modifier = Modifier
                        .graphicsLayer {
                            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                            lerp(
                                start = 0.8f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                )
                                .also{ scale ->
                                    scaleX = scale
                                    scaleY = scale
                                }
                        }
                )
            }
        }


        OutlinedButton(
            onClick = {
                      onSeeMoreButtonClick.invoke()
            },
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.Transparent
            ),
            border = BorderStroke(1.dp, Color(0xFFFF85AB)),
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .size(width = 160.dp, height = 40.dp)
        ) {
            Text(
                text = "Ver mais",
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color(0xFFFF85AB)
            )
        }
    }


}

@Preview
@Composable
fun PostsListScreenPreview() {
    PostsListScreen(
        paddingValues = PaddingValues(),
        onSeeMoreButtonClick = {}
    )
}