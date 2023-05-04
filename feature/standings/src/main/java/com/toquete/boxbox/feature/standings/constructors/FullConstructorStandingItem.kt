package com.toquete.boxbox.feature.standings.constructors

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.toquete.boxbox.core.model.Constructor
import com.toquete.boxbox.core.model.FullConstructorStanding
import com.toquete.boxbox.core.ui.custom.BoxBoxAsyncImage
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import com.toquete.boxbox.core.ui.theme.FormulaOne
import com.toquete.boxbox.feature.standings.R
import com.toquete.boxbox.feature.standings.ui.StandingInfoSurface

@Composable
internal fun FullConstructorStandingItem(standing: FullConstructorStanding) {
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
                    modifier = Modifier.padding(8.dp),
                    data = standing.constructor.imageUrl,
                    contentDescription = null
                )
            }
        }
        Divider(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .testTag("Divider"),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            StandingInfoSurface {
                Text(
                    modifier = Modifier
                        .padding(6.dp)
                        .testTag("Points"),
                    text = stringResource(R.string.points, standing.points),
                    style = MaterialTheme.typography.bodyMedium.copy(fontFamily = FormulaOne)
                )
            }
            StandingInfoSurface {
                Text(
                    modifier = Modifier
                        .padding(6.dp)
                        .testTag("Wins"),
                    text = pluralStringResource(R.plurals.wins, standing.wins.toInt(), standing.wins),
                    style = MaterialTheme.typography.bodyMedium.copy(fontFamily = FormulaOne)
                )
            }
        }
    }
}

@Preview(name = "Item Light", showBackground = true)
@Composable
private fun ConstructorStandingItemLightPreview() {
    BoxBoxTheme {
        FullConstructorStandingItem(
            standing = FullConstructorStanding(
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
    }
}

@Preview(name = "Item Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ConstructorStandingItemDarkPreview() {
    BoxBoxTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            FullConstructorStandingItem(
                standing = FullConstructorStanding(
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
        }
    }
}
