package com.toquete.boxbox.feature.raceresults.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.toquete.boxbox.core.model.Constructor
import com.toquete.boxbox.core.model.Driver
import com.toquete.boxbox.core.model.RaceResult
import com.toquete.boxbox.core.ui.annotation.UiModePreviews
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import com.toquete.boxbox.core.ui.theme.FormulaOne

@Composable
internal fun RaceResultItem(
    raceResult: RaceResult,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .wrapContentWidth(unbounded = true)
            .padding(all = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .width(50.dp)
                .padding(end = 4.dp),
            text = raceResult.position.toString(),
            style = MaterialTheme.typography.bodyLarge.copy(fontFamily = FormulaOne)
        )
        Text(
            modifier = Modifier
                .width(200.dp)
                .padding(end = 4.dp),
            text = "${raceResult.driver.firstName} ${raceResult.driver.lastName}",
            style = MaterialTheme.typography.bodyLarge.copy(fontFamily = FormulaOne)
        )
        Text(
            modifier = Modifier
                .width(150.dp)
                .padding(end = 4.dp),
            text = raceResult.constructor.name,
            style = MaterialTheme.typography.bodyLarge.copy(fontFamily = FormulaOne)
        )
        Text(
            modifier = Modifier
                .width(60.dp)
                .padding(end = 4.dp),
            text = raceResult.laps,
            style = MaterialTheme.typography.bodyLarge.copy(fontFamily = FormulaOne)
        )
        Text(
            modifier = Modifier
                .width(125.dp)
                .padding(end = 4.dp),
            text = raceResult.time.orEmpty(),
            style = MaterialTheme.typography.bodyLarge.copy(fontFamily = FormulaOne)
        )
        Text(
            modifier = Modifier
                .width(125.dp)
                .padding(end = 4.dp),
            text = raceResult.status,
            style = MaterialTheme.typography.bodyLarge.copy(fontFamily = FormulaOne)
        )
        Text(
            modifier = Modifier.width(50.dp),
            text = raceResult.points.toString(),
            style = MaterialTheme.typography.bodyLarge.copy(fontFamily = FormulaOne)
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
