package com.toquete.boxbox.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.toquete.boxbox.feature.home.navigation.HOME_ROUTE
import com.toquete.boxbox.feature.home.navigation.homeScreen
import com.toquete.boxbox.feature.raceresults.navigation.navigateToRaceResult
import com.toquete.boxbox.feature.raceresults.navigation.raceResultScreen
import com.toquete.boxbox.feature.races.navigation.racesScreen
import com.toquete.boxbox.feature.standings.navigation.standingsScreen

@Composable
fun BoxBoxNavHost(
    isOffline: Boolean,
    isSyncing: Boolean,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = HOME_ROUTE,
    onSettingsButtonClick: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        homeScreen(
            isOffline = isOffline,
            isSyncing = isSyncing,
            onSettingsButtonClick = onSettingsButtonClick
        ) {
            addHomeGraph(
                onRaceItemClick = navController::navigateToRaceResult
            )
        }
        raceResultScreen(onNavigateUp = navController::navigateUp)
    }
}

private fun NavGraphBuilder.addHomeGraph(
    onRaceItemClick: (Int, String) -> Unit
) {
    standingsScreen()
    racesScreen(onRaceClick = onRaceItemClick)
}
