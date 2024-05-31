package com.toquete.boxbox.feature.raceresults.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.res.stringResource
import com.toquete.boxbox.core.model.Constructor
import com.toquete.boxbox.core.model.Driver
import com.toquete.boxbox.core.model.RaceResult
import com.toquete.boxbox.core.ui.annotation.UiModePreviews
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import com.toquete.boxbox.feature.raceresults.model.RaceResultsTab
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RaceAndSprintResult(
    raceResults: List<RaceResult>,
    sprintResults: List<RaceResult>
) {
    var selectedTab by remember { mutableStateOf(RaceResultsTab.RACE) }
    val pagerState = rememberPagerState(
        pageCount = { RaceResultsTab.entries.size }
    )
    val coroutineScope = rememberCoroutineScope()
    val pages = RaceResultsTab.entries.toTypedArray()

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            selectedTab = pages[page]
        }
    }

    Column {
        TabRow(selectedTabIndex = pages.indexOf(selectedTab)) {
            pages.forEachIndexed { index, raceResultsTab ->
                Tab(
                    selected = selectedTab == raceResultsTab,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = { Text(stringResource(raceResultsTab.titleId)) }
                )
            }
        }
        HorizontalPager(state = pagerState) { page ->
            when (pages[page]) {
                RaceResultsTab.RACE -> RaceResultTable(raceResults)
                RaceResultsTab.SPRINT -> RaceResultTable(sprintResults)
            }
        }
    }
}

@UiModePreviews
@Composable
internal fun RaceAndSprintResultPreview() {
    BoxBoxTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            RaceAndSprintResult(
                raceResults = listOf(
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
                ),
                sprintResults = listOf(
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
        }
    }
}
