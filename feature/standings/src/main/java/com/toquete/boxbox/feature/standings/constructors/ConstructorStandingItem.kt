package com.toquete.boxbox.feature.standings.constructors

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.decode.SvgDecoder
import com.toquete.boxbox.core.model.Constructor
import com.toquete.boxbox.core.model.ConstructorStanding
import com.toquete.boxbox.core.ui.annotation.UiModePreviews
import com.toquete.boxbox.core.ui.custom.BoxBoxAsyncImage
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import com.toquete.boxbox.core.ui.theme.FormulaOne
import com.toquete.boxbox.feature.standings.R
import com.toquete.boxbox.core.ui.R as uiR

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun ConstructorStandingItem(standing: ConstructorStanding) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier
                    .weight(weight = 0.6f)
                    .wrapContentHeight(unbounded = true),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.testTag("Position"),
                    text = standing.position.toString(),
                    style = MaterialTheme.typography.displaySmall.copy(fontFamily = FormulaOne)
                )
                Text(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .testTag("Constructor Name"),
                    text = standing.constructor.name,
                    style = MaterialTheme.typography.headlineMedium.copy(fontFamily = FormulaOne),
                    fontWeight = FontWeight.Bold
                )
            }
            Surface(
                modifier = Modifier
                    .size(120.dp)
                    .weight(weight = 0.4f, fill = false),
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.inverseOnSurface
            ) {
                BoxBoxAsyncImage(
                    modifier = Modifier
                        .padding(8.dp)
                        .testTag("Constructor Image"),
                    data = standing.constructor.imageUrl,
                    placeholder = uiR.drawable.ic_construction,
                    error = uiR.drawable.ic_construction,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.inverseSurface)
                )
            }
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
                }
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
                            .testTag("Flag"),
                        data = standing.constructor.flagUrl,
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

@UiModePreviews
@Composable
internal fun ConstructorStandingItemPreview() {
    BoxBoxTheme {
        ConstructorStandingItem(
            standing = ConstructorStanding(
                position = 1,
                points = "258",
                wins = "7",
                constructor = Constructor(
                    id = "red_bull",
                    name = "Red Bull",
                )
            )
        )
    }
}
