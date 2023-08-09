package com.toquete.boxbox.feature.standings.drivers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.toquete.boxbox.core.model.Constructor
import com.toquete.boxbox.core.model.Driver
import com.toquete.boxbox.core.model.DriverStanding
import com.toquete.boxbox.core.ui.annotation.UiModePreviews
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import com.toquete.boxbox.feature.standings.ui.ScrollToUpButton
import kotlinx.coroutines.launch

@Composable
internal fun DriverStandingsRoute(
    viewModel: DriverStandingsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    DriverStandingsScreen(state)
}

@Composable
internal fun DriverStandingsScreen(state: DriverStandingsState) {
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val showButton by remember {
        derivedStateOf { lazyListState.firstVisibleItemIndex > 0 }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .testTag("Driver Standings List"),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            state = lazyListState
        ) {
            items(state.standings) { standing ->
                DriverStandingItem(standing)
            }
        }
        ScrollToUpButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .testTag("Scroll Button"),
            visible = showButton,
            onClick = {
                coroutineScope.launch {
                    lazyListState.animateScrollToItem(index = 0)
                }
            }
        )
    }
}

@UiModePreviews
@Composable
private fun FullDriverStandingsScreenPreview() {
    BoxBoxTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            DriverStandingsScreen(
                state = DriverStandingsState(
                    standings = listOf(
                        DriverStanding(
                            position = 1,
                            points = "258",
                            wins = "7",
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
                                imageUrl = null,
                                flagUrl = null
                            )
                        )
                    )
                )
            )
        }
    }
}
