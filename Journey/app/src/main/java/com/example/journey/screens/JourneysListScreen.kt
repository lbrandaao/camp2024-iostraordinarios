package com.example.journey.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.journey.components.utils.JourneyComponent
import com.example.journey.ui.theme.Poppins
import com.example.journey.viewModels.JourneyViewModel

@Composable
fun JourneysListScreen(
    paddingValues: PaddingValues,
    journeyViewModel: JourneyViewModel,
    onJourneyDetailsClick: () -> Unit
) {
    if (journeyViewModel.listJourneys() == null) journeyViewModel.loadJourneys()

    if (journeyViewModel.isReady()) {
        val journeysList = remember {journeyViewModel.listJourneys() ?: listOf()}

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    end = 16.dp
                ),
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            item {
                Text(
                    text = "Comece sua jornada por aqui: ",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    lineHeight = 37.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = paddingValues.calculateTopPadding() + 16.dp)

                )
            }

            repeat(journeysList.size - 1) {
                item {
                    JourneyComponent(
                        title = journeysList[it].title,
                        publisherName = journeysList[it].creator.fullName,
                        tagsList = journeysList[it].tags.map { it.name },
                        superpowersList = journeysList[it].superpowers.map { it.name },
                        onClick = {
                            journeyViewModel.setSelectedJourney(journeysList[it])
                            onJourneyDetailsClick.invoke()
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            if (journeysList.isNotEmpty()) {
                item {
                    JourneyComponent(
                        title = journeysList.last().title,
                        publisherName = journeysList.last().creator.fullName,
                        tagsList = journeysList.last().tags.map { it.name },
                        superpowersList = journeysList.last().superpowers.map { it.name },
                        onClick = {
                            journeyViewModel.setSelectedJourney(journeysList.last())
                            onJourneyDetailsClick.invoke()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = paddingValues.calculateBottomPadding() + 10.dp)
                    )
                }
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