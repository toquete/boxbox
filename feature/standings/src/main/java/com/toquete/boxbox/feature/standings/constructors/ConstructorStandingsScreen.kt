package com.toquete.boxbox.feature.standings.constructors

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
import com.toquete.boxbox.core.model.ConstructorStanding
import com.toquete.boxbox.core.ui.annotation.UiModePreviews
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import com.toquete.boxbox.feature.standings.ui.ScrollToUpButton
import kotlinx.coroutines.launch

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
    val coroutineScope = rememberCoroutineScope()
    val showButton by remember {
        derivedStateOf { lazyListState.firstVisibleItemIndex > 0 }
    }
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
