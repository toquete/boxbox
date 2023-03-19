package com.toquete.boxbox.features.standings.driver

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.toquete.boxbox.domain.model.DriverStanding
import com.toquete.boxbox.ui.theme.BoxBoxTheme

@Composable
fun DriverStandingsScreen(viewModel: DriverStandingsViewModel = viewModel()) {
    DriverStandingsContent(viewModel.state)
}

@Composable
private fun DriverStandingsContent(state: DriverStandingsState) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            DriversStandingsList(state.standings)
        }
    }
}

@Composable
private fun DriversStandingsList(list: List<DriverStanding>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(list) { standing ->
            DriverStandingItem(standing)
            Divider()
        }
    }
}

@Preview(name = "Content Light", showBackground = true)
@Composable
private fun DriversStandingContentLightPreview() {
    BoxBoxTheme {
        DriverStandingsContent(
            state = DriverStandingsState(
                standings = listOf(
                    DriverStanding(
                        position = "1",
                        name = "Max",
                        lastName = "Verstappen",
                        nationality = "NED",
                        car = "Red Bull",
                        points = "258"
                    )
                )
            )
        )
    }
}

@Preview(name = "Content Dark", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun DriversStandingItemContentPreview() {
    BoxBoxTheme {
        Surface(color = MaterialTheme.colors.background) {
            DriverStandingsContent(
                state = DriverStandingsState(
                    standings = listOf(
                        DriverStanding(
                            position = "1",
                            name = "Max",
                            lastName = "Verstappen",
                            nationality = "NED",
                            car = "Red Bull",
                            points = "258"
                        )
                    )
                )
            )
        }
    }
}