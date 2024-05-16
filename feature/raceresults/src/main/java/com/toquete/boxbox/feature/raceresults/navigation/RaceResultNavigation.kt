package com.toquete.boxbox.feature.raceresults.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.toquete.boxbox.feature.raceresults.ui.RaceResultRoute

const val RACE_RESULT_ROUTE = "race_result_route/{round}/{race}"
const val ROUND_ARGUMENT = "round"
const val RACE_ARGUMENT = "race"

fun NavController.navigateToRaceResult(round: Int, race: String) {
    navigate(
        RACE_RESULT_ROUTE.replace("{$ROUND_ARGUMENT}", round.toString())
            .replace("{$RACE_ARGUMENT}", race)
    )
}

fun NavGraphBuilder.raceResultScreen(onNavigateUp: () -> Unit) {
    composable(
        route = RACE_RESULT_ROUTE,
        arguments = listOf(
            navArgument(ROUND_ARGUMENT) {
                type = NavType.IntType
            },
            navArgument(RACE_ARGUMENT) {
                type = NavType.StringType
            }
        )
    ) {
        RaceResultRoute(onNavigateUp)
    }
}
