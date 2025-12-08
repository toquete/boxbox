package com.toquete.boxbox.feature.raceresults.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.toquete.boxbox.core.navigation.BoxBoxRoute
import com.toquete.boxbox.feature.raceresults.ui.RaceResultRoute

fun NavController.navigateToRaceResult(round: Int, race: String) {
    navigate(BoxBoxRoute.RaceResult(round, race))
}

fun NavGraphBuilder.raceResultScreen(onNavigateUp: () -> Unit) {
    composable<BoxBoxRoute.RaceResult> {
        RaceResultRoute(onNavigateUp)
    }
}
