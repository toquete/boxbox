package com.toquete.boxbox.feature.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.toquete.boxbox.feature.home.navigation.HomeDestination

@Composable
internal fun rememberHomeState(
    navController: NavHostController = rememberNavController()
) = remember(navController) {
    HomeState(navController)
}

@Stable
internal class HomeState(val navController: NavHostController) {

    val homeDestinations: List<HomeDestination> = HomeDestination.entries

    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val currentHomeDestination: HomeDestination?
        @Composable get() = currentDestination?.let { destination ->
            homeDestinations.find { it.name.equals(destination.route, ignoreCase = true) }
        }

    fun navigateToHomeDestination(destination: HomeDestination) {
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

        navController.navigate(destination.name.lowercase(), topLevelNavOptions)
    }
}
