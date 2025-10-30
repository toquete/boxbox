package com.toquete.boxbox.feature.standings.constructors

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.toquete.boxbox.core.model.Constructor
import com.toquete.boxbox.core.model.ConstructorStanding
import com.toquete.boxbox.core.ui.annotation.UiModePreviews
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme

@Composable
internal fun ConstructorStandingsRoute(
    viewModel: ConstructorStandingsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ConstructorStandingsScreen(state)
}

@Composable
internal fun ConstructorStandingsScreen(state: ConstructorStandingsState) {
    val lazyListState = rememberLazyListState()
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .testTag("Constructor Standings List"),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            state = lazyListState
        ) {
            items(state.standings) { standing ->
                ConstructorStandingItem(standing)
            }
        }
    }
}

@UiModePreviews
@Composable
internal fun FullConstructorStandingsScreenPreview() {
    BoxBoxTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            ConstructorStandingsScreen(
                state = ConstructorStandingsState(
                    standings = listOf(
                        ConstructorStanding(
                            position = 1,
                            points = "258",
                            wins = "7",
                            constructor = Constructor(
                                id = "red_bull",
                                name = "Red Bull"
                            )
                        )
                    )
                )
            )
        }
    }
}
