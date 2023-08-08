package com.toquete.boxbox.feature.races.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.toquete.boxbox.feature.races.ui.RacesRoute

const val RACES_ROUTE = "races_route"

fun NavController.navigateToRaces() {
    navigate(RACES_ROUTE)
}

fun NavGraphBuilder.racesScreen() {
    composable(route = RACES_ROUTE) {
        RacesRoute()
    }
}
