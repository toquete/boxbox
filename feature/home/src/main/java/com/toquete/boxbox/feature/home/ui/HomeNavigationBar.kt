package com.toquete.boxbox.feature.home.ui

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarScrollBehavior
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import com.toquete.boxbox.feature.home.navigation.HomeDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeNavigationBar(
    homeViewState: HomeViewState,
    scrollBehavior: BottomAppBarScrollBehavior
) {
    BottomAppBar(scrollBehavior = scrollBehavior) {
        homeViewState.homeDestinations.forEach { destination ->
            val isSelected = homeViewState.currentDestination.isTopLevelDestinationInHierarchy(destination)
            NavigationBarItem(
                modifier = Modifier.testTag("Home Navigation Bar Item ${destination.name}"),
                selected = isSelected,
                onClick = { homeViewState.navigateToHomeDestination(destination) },
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
        it.hasRoute(destination.route::class)
    } ?: false
