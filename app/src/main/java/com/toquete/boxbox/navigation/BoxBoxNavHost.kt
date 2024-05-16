package com.toquete.boxbox.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.toquete.boxbox.feature.raceresults.navigation.navigateToRaceResult
import com.toquete.boxbox.feature.raceresults.navigation.raceResultScreen
import com.toquete.boxbox.feature.races.navigation.racesScreen
import com.toquete.boxbox.feature.standings.navigation.STANDINGS_ROUTE
import com.toquete.boxbox.feature.standings.navigation.standingsScreen

@Composable
fun BoxBoxNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    isOffline: Boolean,
    isSyncing: Boolean,
    startDestination: String = STANDINGS_ROUTE,
    onSettingsButtonClick: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        standingsScreen(
            isOffline = isOffline,
            isSyncing = isSyncing,
            onSettingsButtonClick = onSettingsButtonClick
        )
        racesScreen(onRaceClick = navController::navigateToRaceResult)
        raceResultScreen(onNavigateUp = navController::navigateUp)
    }
}
