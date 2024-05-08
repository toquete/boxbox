package com.toquete.boxbox.feature.raceresults.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.toquete.boxbox.core.model.Constructor
import com.toquete.boxbox.core.model.Driver
import com.toquete.boxbox.core.model.RaceResult
import com.toquete.boxbox.core.ui.annotation.UiModePreviews
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme

@Composable
internal fun RaceResultItem(raceResult: RaceResult) {
    Row(modifier = Modifier.padding(horizontal = 8.dp)) {
        Text(
            modifier = Modifier.width(50.dp),
            text = raceResult.position.toString()
        )
        Text(
            modifier = Modifier
                .width(200.dp)
                .padding(horizontal = 4.dp),
            text = "${raceResult.driver.firstName} ${raceResult.driver.lastName}"
        )
        Text(
            modifier = Modifier.width(200.dp),
            text = raceResult.constructor.name
        )
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
    }
}
