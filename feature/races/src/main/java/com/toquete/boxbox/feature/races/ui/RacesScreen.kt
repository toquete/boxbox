package com.toquete.boxbox.feature.races.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.toquete.boxbox.core.model.Circuit
import com.toquete.boxbox.core.model.Location
import com.toquete.boxbox.core.model.Race
import com.toquete.boxbox.core.ui.annotation.UiModePreviews
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import com.toquete.boxbox.feature.races.model.RacesTab
import kotlinx.coroutines.launch
import kotlinx.datetime.toInstant

@Composable
internal fun RacesRoute(
    viewModel: RacesViewModel = hiltViewModel(),
    onRaceClick: (Int, String) -> Unit = { _, _ -> }
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    RacesScreen(state, onRaceClick)
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun RacesScreen(
    state: RacesState,
    onRaceClick: (Int, String) -> Unit = { _, _ -> }
) {
    var selectedTab by remember { mutableStateOf(RacesTab.UPCOMING) }
    val pagerState = rememberPagerState(
        pageCount = { RacesTab.entries.size }
    )
    val coroutineScope = rememberCoroutineScope()
    val pages = RacesTab.entries.toTypedArray()

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            selectedTab = pages[page]
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        SingleChoiceSegmentedButtonRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
        ) {
            pages.forEachIndexed { index, racesTab ->
                SegmentedButton(
                    selected = index == pages.indexOf(selectedTab),
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    shape = SegmentedButtonDefaults.itemShape(index = index, count = pages.size)
                ) {
                    Text(text = stringResource(racesTab.titleId))
                }
            }
        }
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false
        ) { page ->
            when (pages[page]) {
                RacesTab.UPCOMING -> RacesList(state.upcomingRaces, onRaceClick)
                RacesTab.PAST -> RacesList(state.pastRaces, onRaceClick)
            }
        }
    }
}

@Composable
private fun RacesList(
    list: List<Race>,
    onRaceClick: (Int, String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(list) {
            RaceItem(it, onRaceClick)
        }
    }
}

@UiModePreviews
@Composable
internal fun RacesContentPreview() {
    BoxBoxTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            RacesScreen(
                state = RacesState(
                    upcomingRaces = listOf(
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
