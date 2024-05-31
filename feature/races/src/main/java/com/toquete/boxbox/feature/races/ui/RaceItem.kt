package com.toquete.boxbox.feature.races.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.decode.SvgDecoder
import com.toquete.boxbox.core.common.extension.toDayString
import com.toquete.boxbox.core.common.extension.toShortMonthString
import com.toquete.boxbox.core.model.Circuit
import com.toquete.boxbox.core.model.Location
import com.toquete.boxbox.core.model.Race
import com.toquete.boxbox.core.ui.annotation.UiModePreviews
import com.toquete.boxbox.core.ui.custom.BoxBoxAsyncImage
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import com.toquete.boxbox.core.ui.theme.FormulaOne
import kotlinx.datetime.toInstant
import com.toquete.boxbox.core.ui.R as uiR

@Composable
internal fun RaceItem(race: Race, onClick: (Int, String) -> Unit = { _, _ -> }) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onClick(race.round, race.circuit.country) }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.inverseOnSurface
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                BoxBoxAsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(16.dp)
                        .testTag("Circuit Image"),
                    data = race.circuit.imageUrl,
                    placeholder = uiR.drawable.ic_turn_sharp_right,
                    error = uiR.drawable.ic_turn_sharp_right,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.inverseSurface)
                )
                BoxBoxAsyncImage(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(width = 40.dp, height = 30.dp)
                        .align(Alignment.TopEnd)
                        .testTag("Flag"),
                    data = race.circuit.flagUrl,
                    placeholder = uiR.drawable.ic_public,
                    error = uiR.drawable.ic_public,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant),
                    decoder = SvgDecoder.Factory()
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(weight = 0.6f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row {
                    Text(
                        modifier = Modifier
                            .weight(weight = 0.8f, fill = false)
                            .testTag("Country"),
                        text = race.circuit.country,
                        style = MaterialTheme.typography.headlineMedium.copy(fontFamily = FormulaOne),
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.CenterVertically)
                            .testTag("Chevron"),
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = null
                    )
                }
                HorizontalDivider(
                    modifier = Modifier.testTag("Divider"),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    modifier = Modifier.testTag("Circuit Name"),
                    text = race.circuit.name,
                    style = MaterialTheme.typography.labelLarge.copy(fontFamily = FormulaOne),
                    fontWeight = FontWeight.Normal
                )
            }
            Surface(
                modifier = Modifier.weight(weight = 0.3f, fill = false),
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.surfaceVariant,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurfaceVariant)
            ) {
                Column(
                    modifier = Modifier.padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.testTag("Month"),
                        text = race.dateTime?.toShortMonthString().orEmpty(),
                        style = MaterialTheme.typography.titleMedium.copy(fontFamily = FormulaOne),
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        modifier = Modifier.testTag("Date"),
                        text = race.dateTime?.toDayString().orEmpty(),
                        style = MaterialTheme.typography.headlineSmall.copy(fontFamily = FormulaOne),
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@UiModePreviews
@Composable
internal fun RaceItemPreview() {
    BoxBoxTheme {
        RaceItem(
            race = Race(
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
    }
}
