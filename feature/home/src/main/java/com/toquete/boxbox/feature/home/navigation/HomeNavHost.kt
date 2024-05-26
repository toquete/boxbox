package com.toquete.boxbox.feature.home.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import com.toquete.boxbox.feature.home.ui.HomeState

@Composable
internal fun HomeNavHost(
    modifier: Modifier = Modifier,
    homeState: HomeState,
    startDestination: String = HomeDestination.STANDINGS.name.lowercase(),
    builder: NavGraphBuilder.() -> Unit
) {
    NavHost(
        navController = homeState.navController,
        startDestination = startDestination,
        modifier = modifier,
        builder = builder
    )
}
