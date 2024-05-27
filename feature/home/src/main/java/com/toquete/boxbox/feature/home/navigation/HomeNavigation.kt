package com.toquete.boxbox.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.toquete.boxbox.feature.home.ui.HomeRoute

const val HOME_ROUTE = "home"

fun NavGraphBuilder.homeScreen(
    onSettingsButtonClick: () -> Unit,
    builder: NavGraphBuilder.() -> Unit
) {
    composable(route = HOME_ROUTE) {
        HomeRoute(
            onSettingsButtonClick = onSettingsButtonClick,
            builder = builder
        )
    }
}
