package com.toquete.boxbox.standings.driver.presentation

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.toquete.boxbox.standings.driver.domain.model.DriverStanding
import com.toquete.boxbox.standings.driver.presentation.model.DriversStandingModel
import com.toquete.boxbox.standings.driver.presentation.model.Nationality
import com.toquete.boxbox.ui.theme.BoxBoxTheme

@Composable
fun DriverStandingItem(standing: DriversStandingModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = "#${standing.standing.position}",
            style = MaterialTheme.typography.h4
        )
        Column(
            modifier = Modifier
                .weight(0.8f)
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = standing.standing.driver,
                style = MaterialTheme.typography.h4
            )
            Row {
                Image(
                    modifier = Modifier
                        .size(width = 40.dp, height = 20.dp)
                        .padding(end = 8.dp),
                    contentScale = ContentScale.FillBounds,
                    painter = painterResource(standing.nationality.flagRes),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface)
                        .takeIf { standing.nationality == Nationality.WORLD }
                )
                Text(
                    text = standing.standing.car,
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }
        Text(
            text = standing.standing.points.toString(),
            style = MaterialTheme.typography.h4
        )
    }
}

@Preview(name = "Item Light", showBackground = true)
@Composable
private fun DriversStandingItemLightPreview() {
    BoxBoxTheme {
        DriverStandingItem(
            standing = DriversStandingModel(
                standing = DriverStanding(
                    position = 1,
                    driver = "Max Verstappen",
                    nationality = "NED",
                    car = "Red Bull",
                    points = 258
                ),
                nationality = Nationality.WORLD
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
                standing = DriversStandingModel(
                    standing = DriverStanding(
                        position = 1,
                        driver = "Max Verstappen",
                        nationality = "NED",
                        car = "Red Bull",
                        points = 258
                    ),
                    nationality = Nationality.WORLD
                )
            )
        }
    }
}