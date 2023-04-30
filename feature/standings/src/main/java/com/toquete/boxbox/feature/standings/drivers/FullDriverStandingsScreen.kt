package com.toquete.boxbox.feature.standings.drivers

import android.content.res.Configuration.UI_MODE_NIGHT_YES
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.toquete.boxbox.core.model.Constructor
import com.toquete.boxbox.core.model.Driver
import com.toquete.boxbox.core.model.FullDriverStanding
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import com.toquete.boxbox.feature.standings.ui.ScrollToUpButton
import kotlinx.coroutines.launch

@Composable
fun FullDriverStandingsScreen(list: List<FullDriverStanding>) {
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val showButton by remember {
        derivedStateOf { lazyListState.firstVisibleItemIndex > 0 }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            state = lazyListState
        ) {
            items(list) { standing ->
                FullDriverStandingItem(standing)
            }
        }
        ScrollToUpButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            visible = showButton,
            onClick = {
                coroutineScope.launch {
                    lazyListState.animateScrollToItem(index = 0)
                }
            }
        )
    }
}

@Preview(name = "Content Light", showBackground = true)
@Composable
private fun FFullDriverStandingsScreenLightPreview() {
    BoxBoxTheme {
        FullDriverStandingsScreen(
            list = listOf(
                FullDriverStanding(
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
                        imageUrl = null
                    )
                )
            )
        )
    }
}

@Preview(name = "Content Dark", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun FullDriverStandingsScreenDarkPreview() {
    BoxBoxTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            FullDriverStandingsScreen(
                list = listOf(
                    FullDriverStanding(
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
                            imageUrl = null
                        )
                    )
                )
            )
        }
    }
}