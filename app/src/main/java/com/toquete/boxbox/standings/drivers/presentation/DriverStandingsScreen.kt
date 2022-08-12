package com.toquete.boxbox.standings.drivers.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.toquete.boxbox.standings.drivers.domain.model.DriverStanding
import com.toquete.boxbox.ui.theme.BoxBoxTheme

@Composable
fun DriverStandingsScreen(viewModel: DriverStandingsViewModel = viewModel()) {
    val state = viewModel.state
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(state.standings) { standing ->
            DriverStandingItem(standing)
            Divider()
        }
    }
}

@Composable
fun DriverStandingItem(standing: DriverStanding) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = "#${standing.position}",
            style = MaterialTheme.typography.h4
        )
        Column(
            modifier = Modifier
                .weight(0.8f)
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = standing.driver,
                style = MaterialTheme.typography.h4
            )
            Row {
                Text(
                    modifier = Modifier.padding(end = 8.dp),
                    text = standing.nationality,
                    style = MaterialTheme.typography.subtitle1
                )
                Text(
                    text = standing.car,
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }
        Text(
            text = standing.points.toString(),
            style = MaterialTheme.typography.h4
        )
    }
}

@Preview(name = "Item Light", showBackground = true)
@Composable
fun DriversStandingItemLightPreview() {
    BoxBoxTheme {
        DriverStandingItem(
            standing = DriverStanding(
                position = 1,
                driver = "Max Verstappen",
                nationality = "NED",
                car = "Red Bull",
                points = 258
            )
        )
    }
}

@Preview(name = "Item Dark", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DriversStandingItemDarkPreview() {
    BoxBoxTheme {
        Surface(color = MaterialTheme.colors.background) {
            DriverStandingItem(
                standing = DriverStanding(
                    position = 1,
                    driver = "Max Verstappen",
                    nationality = "NED",
                    car = "Red Bull",
                    points = 258
                )
            )
        }
    }
}