package com.toquete.boxbox.feature.raceresults.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.toquete.boxbox.core.navigation.RaceResultRoute
import com.toquete.boxbox.feature.raceresults.ui.RaceResultRoute as RaceResultScreen

fun NavController.navigateToRaceResult(round: Int, race: String) {
    navigate(RaceResultRoute(round, race))
}

fun NavGraphBuilder.raceResultScreen(onNavigateUp: () -> Unit) {
    composable<RaceResultRoute> {
        RaceResultScreen(onNavigateUp)
    }
}
