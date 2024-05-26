package com.toquete.boxbox.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import com.toquete.boxbox.feature.home.navigation.HOME_ROUTE
import com.toquete.boxbox.feature.home.navigation.homeScreen
import com.toquete.boxbox.feature.raceresults.navigation.navigateToRaceResult
import com.toquete.boxbox.feature.raceresults.navigation.raceResultScreen
import com.toquete.boxbox.feature.races.navigation.racesScreen
import com.toquete.boxbox.feature.standings.navigation.standingsScreen
import com.toquete.boxbox.ui.MainAppState

@Composable
fun BoxBoxNavHost(
    mainAppState: MainAppState,
    modifier: Modifier = Modifier,
    startDestination: String = HOME_ROUTE,
    onSettingsButtonClick: () -> Unit
) {
    val isOffline by mainAppState.isOffline.collectAsStateWithLifecycle()
    val isSyncing by mainAppState.isSyncing.collectAsStateWithLifecycle()
    val navController = mainAppState.navController

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        homeScreen(
            isSyncing = isSyncing,
            routes = mainAppState.topLevelRoutes
        ) {
            addHomeGraph(
                isOffline = isOffline,
                isSyncing = isSyncing,
                onSettingsButtonClick = onSettingsButtonClick,
                onRaceItemClick = navController::navigateToRaceResult
            )
        }
        raceResultScreen(onNavigateUp = navController::navigateUp)
    }
}

private fun NavGraphBuilder.addHomeGraph(
    isOffline: Boolean,
    isSyncing: Boolean,
    onSettingsButtonClick: () -> Unit,
    onRaceItemClick: (Int, String) -> Unit
) {
    standingsScreen(
        isOffline = isOffline,
        isSyncing = isSyncing,
        onSettingsButtonClick = onSettingsButtonClick
    )
    racesScreen(onRaceClick = onRaceItemClick)
}
