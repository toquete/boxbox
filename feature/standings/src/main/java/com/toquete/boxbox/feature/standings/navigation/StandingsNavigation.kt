package com.toquete.boxbox.feature.standings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.toquete.boxbox.feature.standings.StandingsScreen

const val STANDINGS_ROUTE = "standings_route"

fun NavController.navigateToStandings() {
    navigate(STANDINGS_ROUTE)
}

fun NavGraphBuilder.standingsScreen() {
    composable(route = STANDINGS_ROUTE) {
        StandingsScreen()
    }
}
