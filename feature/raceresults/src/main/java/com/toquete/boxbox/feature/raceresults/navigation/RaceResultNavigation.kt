package com.toquete.boxbox.feature.raceresults.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.toquete.boxbox.feature.raceresults.ui.RaceResultRoute

const val RACE_RESULT_ROUTE = "race_result_route/{round}"
const val ROUND_ARGUMENT = "round"

fun NavController.navigateToRaceResult(round: Int) {
    navigate(RACE_RESULT_ROUTE.replace("{$ROUND_ARGUMENT}", round.toString()))
}

fun NavGraphBuilder.raceResultScreen() {
    composable(
        route = RACE_RESULT_ROUTE,
        arguments = listOf(
            navArgument(ROUND_ARGUMENT) {
                type = NavType.IntType
            }
        )
    ) {
        RaceResultRoute()
    }
}
