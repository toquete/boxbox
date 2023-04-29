package com.toquete.boxbox.feature.standings.constructors

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import com.toquete.boxbox.model.Constructor
import com.toquete.boxbox.model.FullConstructorStanding

@Composable
fun FullConstructorStandingsScreen() {
    val viewModel: FullConstructorStandingsViewModel = viewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    FullConstructorStandingsContent(state)
}

@Composable
private fun FullConstructorStandingsContent(state: FullConstructorStandingsState) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (state) {
            is FullConstructorStandingsState.Success -> {
                FullConstructorStandingsList(state.data)
            }
        }
    }
}

@Composable
private fun FullConstructorStandingsList(list: List<FullConstructorStanding>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(list) { standing ->
            FullConstructorStandingItem(standing)
        }
    }
}

@Preview(name = "Content Light", showBackground = true)
@Composable
private fun FullConstructorStandingContentLightPreview() {
    BoxBoxTheme {
        FullConstructorStandingsContent(
            state = FullConstructorStandingsState.Success(
                data = listOf(
                    FullConstructorStanding(
                        position = 1,
                        points = "258",
                        wins = "7",
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

@Preview(name = "Content Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FullConstructorStandingItemContentPreview() {
    BoxBoxTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            FullConstructorStandingsContent(
                state = FullConstructorStandingsState.Success(
                    data = listOf(
                        FullConstructorStanding(
                            position = 1,
                            points = "258",
                            wins = "7",
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
