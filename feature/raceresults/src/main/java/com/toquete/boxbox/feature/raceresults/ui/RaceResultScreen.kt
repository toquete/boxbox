package com.toquete.boxbox.feature.raceresults.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.toquete.boxbox.core.model.Constructor
import com.toquete.boxbox.core.model.Driver
import com.toquete.boxbox.core.model.RaceResult
import com.toquete.boxbox.core.ui.annotation.UiModePreviews
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import com.toquete.boxbox.core.ui.theme.FormulaOne
import com.toquete.boxbox.feature.raceresults.R

@Composable
internal fun RaceResultRoute(
    onNavigateUp: () -> Unit,
    viewModel: RaceResultsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    RaceResultScreen(state, onNavigateUp)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RaceResultScreen(
    state: RaceResultsState,
    onNavigateUp: () -> Unit = { }
) {
    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = state.raceName,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontFamily = FormulaOne,
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                navigationIcon = {
                    IconButton(
                        modifier = Modifier.testTag("Back Button"),
                        onClick = onNavigateUp
                    ) {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        val modifier = Modifier.padding(paddingValues)
        when {
            state.results.isEmpty() -> EmptyState(modifier = modifier)
            state.sprintResults.isNotEmpty() -> RaceAndSprintResult(
                modifier = modifier,
                raceResults = state.results,
                sprintResults = state.sprintResults
            )
            else -> RaceResultTable(modifier = modifier, list = state.results)
        }
    }
}

@Composable
internal fun EmptyState(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.race_results_no_results_available),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontFamily = FormulaOne,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@UiModePreviews
@Composable
internal fun RaceResultPreview() {
    BoxBoxTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            RaceResultScreen(
                state = RaceResultsState(
                    raceName = "Bahrain",
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

@UiModePreviews
@Composable
internal fun EmptyStatePreview() {
    BoxBoxTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            RaceResultScreen(
                state = RaceResultsState(
                    raceName = "Bahrain",
                    results = emptyList()
                )
            )
        }
    }
}
