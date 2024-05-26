package com.toquete.boxbox.feature.home.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.toquete.boxbox.feature.home.navigation.HomeDestination

@Composable
internal fun HomeNavigationBar(homeState: HomeState) {
    NavigationBar {
        homeState.homeDestinations.forEachIndexed { index, destination ->
            val isSelected = homeState.currentDestination.isTopLevelDestinationInHierarchy(destination)
            val route = homeState.routes[index]
            NavigationBarItem(
                selected = isSelected,
                onClick = { homeState.navigateToHomeDestination(route) },
                icon = {
                    Icon(
                        imageVector = if (isSelected) destination.selectedIcon else destination.unselectedIcon,
                        contentDescription = null
                    )
                },
                label = { Text(stringResource(destination.iconTextId)) }
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: HomeDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, ignoreCase = true) ?: false
    } ?: false
