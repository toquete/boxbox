package com.toquete.boxbox.feature.races.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.toquete.boxbox.core.model.Circuit
import com.toquete.boxbox.core.model.Location
import com.toquete.boxbox.core.model.Race
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import kotlinx.datetime.toInstant

@Composable
internal fun RacesRoute(
    viewModel: RacesViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    RacesScreen(state)
}

@Composable
internal fun RacesScreen(state: RacesState) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.races) {
                RaceItem(it)
            }
        }
    }
}

@Preview(name = "Content Light", showBackground = true)
@Composable
private fun RacesContentLightPreview() {
    BoxBoxTheme {
        RacesScreen(
            state = RacesState(
                races = listOf(
                    Race(
                        season = "2023",
                        round = 1,
                        url = "https://en.wikipedia.org/wiki/2023_Bahrain_Grand_Prix",
                        name = "Bahrain Grand Prix",
                        circuit = Circuit(
                            id = "bahrain",
                            url = "http://en.wikipedia.org/wiki/Bahrain_International_Circuit",
                            name = "Bahrain International Circuit",
                            location = Location(
                                latitude = "26.0325",
                                longitude = "50.5106"
                            ),
                            locality = "Sakhir",
                            country = "Bahrain",
                            flagUrl = null,
                            imageUrl = null
                        ),
                        dateTime = "2023-03-05T15:00:00Z".toInstant(),
                        firstPracticeDateTime = "2023-03-03T11:30:00Z".toInstant(),
                        secondPracticeDateTime = "2023-03-03T15:00:00Z".toInstant(),
                        thirdPracticeDateTime = "2023-03-04T11:30:00Z".toInstant(),
                        qualifyingDateTime = "2023-03-04T15:00:00Z".toInstant(),
                        sprintDateTime = null
                    )
                )
            )
        )
    }
}

@Preview(name = "Content Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun RacesContentDarkPreview() {
    BoxBoxTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            RacesScreen(
                state = RacesState(
                    races = listOf(
                        Race(
                            season = "2023",
                            round = 1,
                            url = "https://en.wikipedia.org/wiki/2023_Bahrain_Grand_Prix",
                            name = "Bahrain Grand Prix",
                            circuit = Circuit(
                                id = "bahrain",
                                url = "http://en.wikipedia.org/wiki/Bahrain_International_Circuit",
                                name = "Bahrain International Circuit",
                                location = Location(
                                    latitude = "26.0325",
                                    longitude = "50.5106"
                                ),
                                locality = "Sakhir",
                                country = "Bahrain",
                                flagUrl = null,
                                imageUrl = null
                            ),
                            dateTime = "2023-03-05T15:00:00Z".toInstant(),
                            firstPracticeDateTime = "2023-03-03T11:30:00Z".toInstant(),
                            secondPracticeDateTime = "2023-03-03T15:00:00Z".toInstant(),
                            thirdPracticeDateTime = "2023-03-04T11:30:00Z".toInstant(),
                            qualifyingDateTime = "2023-03-04T15:00:00Z".toInstant(),
                            sprintDateTime = null
                        )
                    )
                )
            )
        }
    }
}
