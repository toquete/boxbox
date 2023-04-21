package com.toquete.boxbox.feature.driverstandings

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import com.toquete.boxbox.model.Constructor
import com.toquete.boxbox.model.Driver
import com.toquete.boxbox.model.FullDriverStanding

@Composable
fun FullDriverStandingItem(standing: FullDriverStanding) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = standing.position.toString(),
            style = MaterialTheme.typography.titleLarge
        )
        Column(
            modifier = Modifier
                .weight(0.8f)
                .padding(horizontal = 8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = standing.driver.firstName,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = standing.driver.lastName,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = standing.constructor.name,
                style = MaterialTheme.typography.titleMedium
            )
        }
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = standing.points,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(name = "Item Light", showBackground = true)
@Composable
private fun DriversStandingItemLightPreview() {
    BoxBoxTheme {
        FullDriverStandingItem(
            standing = FullDriverStanding(
                position = 1,
                points = "258",
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
    }
}

@Preview(name = "Item Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DriversStandingItemDarkPreview() {
    BoxBoxTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            FullDriverStandingItem(
                standing = FullDriverStanding(
                    position = 1,
                    points = "258",
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
        }
    }
}