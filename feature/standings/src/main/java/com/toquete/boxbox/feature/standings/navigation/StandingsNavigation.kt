package com.toquete.boxbox.feature.standings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.toquete.boxbox.feature.standings.StandingsScreen

const val STANDINGS_ROUTE = "standings"

fun NavController.navigateToStandings(navOptions: NavOptions? = null) {
    navigate(STANDINGS_ROUTE, navOptions)
}

fun NavGraphBuilder.standingsScreen() {
    composable(route = STANDINGS_ROUTE) {
        StandingsScreen()
    }
}
