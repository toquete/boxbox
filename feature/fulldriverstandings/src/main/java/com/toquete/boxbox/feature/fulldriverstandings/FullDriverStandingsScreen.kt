package com.toquete.boxbox.feature.fulldriverstandings

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import com.toquete.boxbox.model.Constructor
import com.toquete.boxbox.model.Driver
import com.toquete.boxbox.model.FullDriverStanding

@Composable
fun FullDriverStandingsScreen() {
    val viewModel: FullDriverStandingsViewModel = viewModel()
    val state by viewModel.newState.collectAsStateWithLifecycle()
    FullDriverStandingsContent(state)
}

@Composable
private fun FullDriverStandingsContent(state: FullDriverStandingsState) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (state) {
            FullDriverStandingsState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            is FullDriverStandingsState.Success -> {
                FullDriversStandingsList(state.standings)
            }
        }
    }
}

@Composable
private fun FullDriversStandingsList(list: List<FullDriverStanding>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(list) { standing ->
            FullDriverStandingItem(standing)
            Divider()
        }
    }
}

@Preview(name = "Content Light", showBackground = true)
@Composable
private fun FullDriversStandingContentLightPreview() {
    BoxBoxTheme {
        FullDriverStandingsContent(
            state = FullDriverStandingsState.Success(
                standings = listOf(
                    FullDriverStanding(
                        position = 1,
                        points = "258",
                        driver = Driver(
                            id = "max_verstappen",
                            firstName = "Max",
                            lastName = "Verstappen",
                            imageUrl = null,
                            flagUrl = null
                        ),
                        constructor = Constructor(
                            id = "red_bull",
                            name = "Red Bull",
                            imageUrl = null
                        )
                    )
                )
            )
        )
    }
}

@Preview(name = "Content Dark", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun FullDriversStandingItemContentPreview() {
    BoxBoxTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            FullDriverStandingsContent(
                state = FullDriverStandingsState.Success(
                    standings = listOf(
                        FullDriverStanding(
                            position = 1,
                            points = "258",
                            driver = Driver(
                                id = "max_verstappen",
                                firstName = "Max",
                                lastName = "Verstappen",
                                imageUrl = null,
                                flagUrl = null
                            ),
                            constructor = Constructor(
                                id = "red_bull",
                                name = "Red Bull",
                                imageUrl = null
                            )
                        )
                    )
                )
            )
        }
    }
}