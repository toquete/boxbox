package com.toquete.boxbox.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.toquete.boxbox.feature.home.navigation.HOME_ROUTE
import com.toquete.boxbox.feature.home.navigation.homeScreen
import com.toquete.boxbox.feature.raceresults.navigation.navigateToRaceResult
import com.toquete.boxbox.feature.raceresults.navigation.raceResultScreen
import com.toquete.boxbox.feature.races.navigation.racesScreen
import com.toquete.boxbox.feature.settings.navigation.navigateToSettings
import com.toquete.boxbox.feature.settings.navigation.settingsScreen
import com.toquete.boxbox.feature.standings.navigation.standingsScreen

@Composable
fun BoxBoxNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = HOME_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        homeScreen(
            onSettingsButtonClick = navController::navigateToSettings
        ) {
            standingsScreen()
            racesScreen(onRaceClick = navController::navigateToRaceResult)
        }
        settingsScreen(onDismiss = navController::navigateUp)
        raceResultScreen(onNavigateUp = navController::navigateUp)
    }
}
