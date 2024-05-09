package com.toquete.boxbox.feature.raceresults.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.toquete.boxbox.core.common.extension.isEven
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun RaceResultScreen(state: RaceResultsState) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .horizontalScroll(rememberScrollState())
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            stickyHeader {
                RaceResultHeader()
            }
            itemsIndexed(state.results) { index, raceResult ->
                val background = if (index.isEven()) {
                    MaterialTheme.colorScheme.surfaceVariant
                } else {
                    MaterialTheme.colorScheme.background
                }
                RaceResultItem(
                    modifier = Modifier.background(color = background),
                    raceResult = raceResult
                )
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
                            points = 25,
                            driver = Driver(
                                id = "VER",
                                firstName = "Max",
                                lastName = "Verstappen"
                            ),
                            constructor = Constructor(
                                id = "RBR",
                                name = "Red Bull Racing",
                            ),
                            gridPosition = 1,
                            laps = "57",
                            status = "Finished",
                            time = "1:33:56.736"
                        ),
                        RaceResult(
                            season = "2021",
                            round = 1,
                            position = 1,
                            points = 25,
                            driver = Driver(
                                id = "VER",
                                firstName = "Max",
                                lastName = "Verstappen"
                            ),
                            constructor = Constructor(
                                id = "RBR",
                                name = "Red Bull Racing",
                            ),
                            gridPosition = 1,
                            laps = "57",
                            status = "Finished",
                            time = "1:33:56.736"
                        )
                    )
                )
            )
        }
    }
}
