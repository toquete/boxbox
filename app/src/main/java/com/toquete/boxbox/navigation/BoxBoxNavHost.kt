package com.toquete.boxbox.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.toquete.boxbox.feature.standings.navigation.STANDINGS_ROUTE
import com.toquete.boxbox.feature.standings.navigation.standingsScreen
import com.toquete.boxbox.ui.MainAppState

@Composable
fun BoxBoxNavHost(
    appState: MainAppState,
    modifier: Modifier = Modifier,
    startDestination: String = STANDINGS_ROUTE
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        standingsScreen()
    }
}
