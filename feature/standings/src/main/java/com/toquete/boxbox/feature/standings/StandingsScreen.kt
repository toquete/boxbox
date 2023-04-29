package com.toquete.boxbox.feature.standings

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.toquete.boxbox.feature.standings.constructors.FullConstructorStandingsScreen
import com.toquete.boxbox.feature.standings.drivers.FullDriverStandingsScreen
import com.toquete.boxbox.feature.standings.model.StandingsTab
import com.toquete.boxbox.model.FullConstructorStanding
import com.toquete.boxbox.model.FullDriverStanding

@Composable
fun StandingsScreen(
    driverStandings: List<FullDriverStanding>,
    constructorStandings: List<FullConstructorStanding>
) {
    var selectedTab by remember { mutableStateOf(StandingsTab.DRIVERS) }

    Column {
        TabRow(selectedTabIndex = StandingsTab.values().indexOf(selectedTab)) {
            StandingsTab.values().forEach { standingsTab ->
                Tab(
                    selected = selectedTab == standingsTab,
                    onClick = { selectedTab = standingsTab },
                    text = { Text(stringResource(standingsTab.titleId)) }
                )
            }
        }
        when (selectedTab) {
            StandingsTab.DRIVERS -> FullDriverStandingsScreen(driverStandings)
            StandingsTab.CONSTRUCTORS -> FullConstructorStandingsScreen(constructorStandings)
        }
    }
}