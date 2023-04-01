package com.toquete.boxbox.feature.driverstandings

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import com.toquete.boxbox.model.DriverStanding

@Composable
fun DriverStandingItem(standing: DriverStanding) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = standing.position,
            style = MaterialTheme.typography.h6
        )
        Column(
            modifier = Modifier
                .weight(0.8f)
                .padding(horizontal = 8.dp)
        ) {
            Row {
                Text(
                    text = standing.name,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = standing.lastName,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = standing.car,
                style = MaterialTheme.typography.subtitle1
            )
        }
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = standing.points,
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(name = "Item Light", showBackground = true)
@Composable
private fun DriversStandingItemLightPreview() {
    BoxBoxTheme {
        DriverStandingItem(
            standing = DriverStanding(
                position = "1",
                name = "Max",
                lastName = "Verstappen",
                nationality = "NED",
                car = "Red Bull",
                points = "258"
            )
        )
    }
}

@Preview(name = "Item Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DriversStandingItemDarkPreview() {
    BoxBoxTheme {
        Surface(color = MaterialTheme.colors.background) {
            DriverStandingItem(
                standing = DriverStanding(
                    position = "1",
                    name = "Max",
                    lastName = "Verstappen",
                    nationality = "NED",
                    car = "Red Bull",
                    points = "258"
                )
            )
        }
    }
}