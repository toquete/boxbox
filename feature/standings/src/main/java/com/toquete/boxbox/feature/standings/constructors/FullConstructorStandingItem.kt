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
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import com.toquete.boxbox.core.ui.theme.FormulaOne
import com.toquete.boxbox.feature.standings.R
import com.toquete.boxbox.model.Constructor
import com.toquete.boxbox.model.FullConstructorStanding

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
                    .weight(0.6f)
                    .wrapContentHeight(unbounded = true),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = standing.position.toString(),
                    style = MaterialTheme.typography.displaySmall.copy(
                        fontFamily = FormulaOne
                    )
                )
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = standing.constructor.name,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontFamily = FormulaOne
                    ),
                    fontWeight = FontWeight.Bold
                )
            }
            Surface(
                modifier = Modifier
                    .size(120.dp)
                    .weight(0.4f, fill = false),
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.inverseOnSurface
            ) {
                AsyncImage(
                    modifier = Modifier.padding(8.dp),
                    model = standing.constructor.imageUrl,
                    contentDescription = null
                )
            }
        }
        Divider(
            modifier = Modifier.padding(horizontal = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Surface(
                shape = MaterialTheme.shapes.extraLarge,
                color = MaterialTheme.colorScheme.secondary
            ) {
                Text(
                    modifier = Modifier.padding(6.dp),
                    text = stringResource(R.string.points, standing.points),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = FormulaOne
                    )
                )
            }
            Surface(
                shape = MaterialTheme.shapes.extraLarge,
                color = MaterialTheme.colorScheme.secondary
            ) {
                Text(
                    modifier = Modifier.padding(6.dp),
                    text = pluralStringResource(R.plurals.wins, standing.wins.toInt(), standing.wins),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = FormulaOne
                    )
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