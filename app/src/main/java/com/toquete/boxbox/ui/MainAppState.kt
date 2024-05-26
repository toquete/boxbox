package com.toquete.boxbox.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.toquete.boxbox.core.common.STATE_FLOW_STOP_TIMEOUT
import com.toquete.boxbox.feature.races.navigation.RACES_ROUTE
import com.toquete.boxbox.feature.races.navigation.navigateToRaces
import com.toquete.boxbox.feature.standings.navigation.STANDINGS_ROUTE
import com.toquete.boxbox.feature.standings.navigation.navigateToStandings
import com.toquete.boxbox.navigation.TopLevelDestination
import com.toquete.boxbox.util.monitor.NetworkMonitor
import com.toquete.boxbox.util.monitor.SyncMonitor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Composable
fun rememberMainAppState(
    networkMonitor: NetworkMonitor,
    syncMonitor: SyncMonitor,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController()
) = remember(networkMonitor, syncMonitor, coroutineScope) {
    MainAppState(networkMonitor, syncMonitor, coroutineScope, navController)
}

@Stable
class MainAppState(
    networkMonitor: NetworkMonitor,
    syncMonitor: SyncMonitor,
    coroutineScope: CoroutineScope,
    val navController: NavHostController
) {

    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(STATE_FLOW_STOP_TIMEOUT),
            initialValue = false
        )

    val isSyncing = syncMonitor.isSyncing
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(STATE_FLOW_STOP_TIMEOUT),
            initialValue = true
        )

    val hasFailed = syncMonitor.hasFailed
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(STATE_FLOW_STOP_TIMEOUT),
            initialValue = false
        )

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    val topLevelRoutes: List<String> = topLevelDestinations.map { it.route }

    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            STANDINGS_ROUTE -> TopLevelDestination.STANDINGS
            RACES_ROUTE -> TopLevelDestination.RACES
            else -> null
        }

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }

        when (topLevelDestination) {
            TopLevelDestination.STANDINGS -> navController.navigateToStandings(topLevelNavOptions)
            TopLevelDestination.RACES -> navController.navigateToRaces(topLevelNavOptions)
        }
    }
}
