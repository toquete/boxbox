package com.toquete.boxbox.feature.standings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.toquete.boxbox.core.ui.BoxBoxRoute
import com.toquete.boxbox.feature.standings.StandingsScreen

fun NavController.navigateToStandings(navOptions: NavOptions? = null) {
    navigate(BoxBoxRoute.Standings, navOptions)
}

fun NavGraphBuilder.standingsScreen() {
    composable<BoxBoxRoute.Standings> {
        StandingsScreen()
    }
}
