package com.toquete.boxbox.feature.raceresults.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.toquete.boxbox.core.model.Constructor
import com.toquete.boxbox.core.model.Driver
import com.toquete.boxbox.core.model.RaceResult
import com.toquete.boxbox.core.ui.annotation.UiModePreviews
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme

@Composable
internal fun RaceResultItem(raceResult: RaceResult) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = raceResult.position.toString())
        Text(text = "${raceResult.driver.firstName} ${raceResult.driver.lastName}")
        Text(text = raceResult.constructor.name)
    }
}

@UiModePreviews
@Composable
internal fun RaceResultItemPreview() {
    BoxBoxTheme {
        RaceResultItem(
            raceResult = RaceResult(
                season = "2021",
                round = 1,
                position = 1,
                driver = Driver(
                    id = "VER",
                    firstName = "Max",
                    lastName = "Verstappen"
                ),
                constructor = Constructor(
                    id = "RBR",
                    name = "Red Bull Racing",
                ),
                gridPosition = 1
            )
        )
    }
}
