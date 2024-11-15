package com.toquete.boxbox.feature.raceresults.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.toquete.boxbox.core.common.extension.isEven
import com.toquete.boxbox.core.model.Constructor
import com.toquete.boxbox.core.model.Driver
import com.toquete.boxbox.core.model.RaceResult
import com.toquete.boxbox.core.ui.annotation.UiModePreviews
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RaceResultTable(list: List<RaceResult>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .horizontalScroll(rememberScrollState()),
    ) {
        stickyHeader {
            RaceResultHeader()
        }
        itemsIndexed(list) { index, raceResult ->
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

@UiModePreviews
@Composable
internal fun RaceResultTablePreview() {
    BoxBoxTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            RaceResultTable(
                list = listOf(
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
