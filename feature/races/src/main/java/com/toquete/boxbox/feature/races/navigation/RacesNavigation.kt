package com.toquete.boxbox.feature.races.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.toquete.boxbox.core.navigation.RacesRoute
import com.toquete.boxbox.feature.races.ui.RacesRoute as RacesScreen

fun NavController.navigateToRaces(navOptions: NavOptions? = null) {
    navigate(RacesRoute, navOptions)
}

fun NavGraphBuilder.racesScreen(onRaceClick: (Int, String) -> Unit = { _, _ -> }) {
    composable<RacesRoute> {
        RacesScreen(onRaceClick = onRaceClick)
    }
}
