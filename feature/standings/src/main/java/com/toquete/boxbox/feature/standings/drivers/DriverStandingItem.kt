package com.toquete.boxbox.feature.standings.drivers

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import coil.compose.AsyncImagePainter
import coil.decode.SvgDecoder
import com.toquete.boxbox.core.model.Constructor
import com.toquete.boxbox.core.model.Driver
import com.toquete.boxbox.core.model.DriverStanding
import com.toquete.boxbox.core.ui.annotation.UiModePreviews
import com.toquete.boxbox.core.ui.custom.BoxBoxAsyncImage
import com.toquete.boxbox.core.ui.custom.CardSide
import com.toquete.boxbox.core.ui.custom.FlipCard
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import com.toquete.boxbox.core.ui.theme.FormulaOne
import com.toquete.boxbox.feature.standings.R
import com.toquete.boxbox.core.ui.R as uiR

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DriverStandingItem(standing: DriverStanding) {
    var cardSide by rememberSaveable { mutableStateOf(CardSide.FRONT) }
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurfaceVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(weight = 0.6f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.testTag("Position"),
                    text = standing.position.toString(),
                    style = MaterialTheme.typography.displaySmall.copy(fontFamily = FormulaOne)
                )
                Column {
                    Text(
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .testTag("First Name"),
                        text = standing.driver.firstName,
                        style = MaterialTheme.typography.headlineMedium.copy(fontFamily = FormulaOne),
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        modifier = Modifier.testTag("Last Name"),
                        text = standing.driver.lastName,
                        style = MaterialTheme.typography.headlineMedium.copy(fontFamily = FormulaOne),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            FlipCard(
                side = cardSide,
                onClick = { cardSide = cardSide.flip() },
                modifier = Modifier
                    .size(120.dp)
                    .weight(weight = 0.4f, fill = false),
                front = { DriverImage(standing) },
                back = { DriverNumber(standing) }
            )
        }
        HorizontalDivider(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .testTag("Divider"),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SuggestionChip(
                onClick = { },
                label = {
                    Text(
                        modifier = Modifier.testTag("Points"),
                        text = stringResource(R.string.standings_points, standing.points),
                        style = MaterialTheme.typography.bodySmall.copy(fontFamily = FormulaOne)
                    )
                },
                colors = SuggestionChipDefaults.suggestionChipColors()
            )
            SuggestionChip(
                onClick = { },
                label = {
                    Text(
                        modifier = Modifier.testTag("Wins"),
                        text = pluralStringResource(R.plurals.standings_wins, standing.wins.toInt(), standing.wins),
                        style = MaterialTheme.typography.bodySmall.copy(fontFamily = FormulaOne)
                    )
                }
            )
            SuggestionChip(
                onClick = { },
                label = {
                    BoxBoxAsyncImage(
                        modifier = Modifier
                            .size(width = 40.dp, height = 20.dp)
                            .testTag("Constructor"),
                        data = standing.constructor.imageUrl,
                        placeholder = uiR.drawable.ic_construction,
                        error = uiR.drawable.ic_construction,
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant),
                    )
                }
            )
            SuggestionChip(
                onClick = { },
                label = {
                    BoxBoxAsyncImage(
                        modifier = Modifier
                            .size(width = 40.dp, height = 20.dp)
                            .testTag("Flag"),
                        data = standing.driver.flagUrl,
                        placeholder = uiR.drawable.ic_public,
                        error = uiR.drawable.ic_public,
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant),
                        decoder = SvgDecoder.Factory()
                    )
                }
            )
        }
    }
}

@Composable
private fun DriverImage(standing: DriverStanding) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceContainer
    ) {
        BoxBoxAsyncImage(
            modifier = Modifier.testTag("Driver"),
            data = standing.driver.imageUrl,
            placeholder = uiR.drawable.ic_person,
            error = uiR.drawable.ic_person,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.inverseSurface)
        )
    }
}

@Composable
fun DriverNumber(standing: DriverStanding) {
    val backgroundColor = standing.constructor.backgroundColor?.let { color ->
        Color(color.toColorInt())
    } ?: MaterialTheme.colorScheme.inverseOnSurface
    var isLoadingOrError by remember {
        mutableStateOf(true)
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        shape = MaterialTheme.shapes.medium,
        color = if (isLoadingOrError) MaterialTheme.colorScheme.inverseOnSurface else backgroundColor
    ) {
        BoxBoxAsyncImage(
            modifier = Modifier.testTag("Number"),
            data = standing.driver.numberUrl,
            placeholder = uiR.drawable.ic_numbers,
            error = uiR.drawable.ic_numbers,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.inverseSurface),
            onState = { state ->
                isLoadingOrError = state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error
            }
        )
    }
}

@UiModePreviews
@Composable
internal fun DriverStandingItemPreview() {
    BoxBoxTheme {
        DriverStandingItem(
            standing = DriverStanding(
                position = 1,
                points = "258",
                wins = "7",
                driver = Driver(
                    id = "max_verstappen",
                    firstName = "Max",
                    lastName = "Verstappen"
                ),
                constructor = Constructor(
                    id = "red_bull",
                    name = "Red Bull"
                )
            )
        )
    }
}
