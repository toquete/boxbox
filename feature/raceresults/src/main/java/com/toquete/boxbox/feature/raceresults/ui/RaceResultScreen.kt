package com.toquete.boxbox.feature.raceresults.ui

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.toquete.boxbox.core.model.Constructor
import com.toquete.boxbox.core.model.Driver
import com.toquete.boxbox.core.model.RaceResult
import com.toquete.boxbox.core.ui.annotation.UiModePreviews
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme

@Composable
internal fun RaceResultRoute(
    viewModel: RaceResultsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    RaceResultScreen(state)
}

@Composable
internal fun RaceResultScreen(state: RaceResultsState) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .horizontalScroll(rememberScrollState())
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(state.results) {
                RaceResultItem(it)
                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider(modifier = Modifier.width(500.dp))
            }
        }
    }
}

@UiModePreviews
@Composable
internal fun RaceResultPreview() {
    BoxBoxTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            RaceResultScreen(
                state = RaceResultsState(
                    results = listOf(
                        RaceResult(
                            season = "2021",
                            round = 1,
                            position = 1,
                            driver = Driver(
                                id = "VER",
                                firstName = "Max",
                                lastName = "Verstappen"
                            ),
                            constructor = Constructor(
                                id = "RBR",
                                name = "Red Bull Racing",
                            ),
                            gridPosition = 1
                        ),
                        RaceResult(
                            season = "2021",
                            round = 1,
                            position = 1,
                            driver = Driver(
                                id = "VER",
                                firstName = "Max",
                                lastName = "Verstappen"
                            ),
                            constructor = Constructor(
                                id = "RBR",
                                name = "Red Bull Racing",
                            ),
                            gridPosition = 1
                        )
                    )
                )
            )
        }
    }
}
