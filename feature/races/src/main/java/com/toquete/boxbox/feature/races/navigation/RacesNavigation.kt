package com.toquete.boxbox.feature.races.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.toquete.boxbox.feature.races.ui.RacesRoute

const val RACES_ROUTE = "races_route"

fun NavController.navigateToRaces(navOptions: NavOptions? = null) {
    navigate(RACES_ROUTE, navOptions)
}

fun NavGraphBuilder.racesScreen(onRaceClick: (Int) -> Unit = {}) {
    composable(route = RACES_ROUTE) {
        RacesRoute(onRaceClick = onRaceClick)
    }
}
