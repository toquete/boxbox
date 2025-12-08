package com.toquete.boxbox.feature.races.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.toquete.boxbox.core.ui.BoxBoxRoute
import com.toquete.boxbox.feature.races.ui.RacesRoute

fun NavController.navigateToRaces(navOptions: NavOptions? = null) {
    navigate(BoxBoxRoute.Races, navOptions)
}

fun NavGraphBuilder.racesScreen(onRaceClick: (Int, String) -> Unit = { _, _ -> }) {
    composable<BoxBoxRoute.Races> {
        RacesRoute(onRaceClick = onRaceClick)
    }
}
