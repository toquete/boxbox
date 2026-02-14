package com.toquete.boxbox.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.toquete.boxbox.core.navigation.BoxBoxRoute
import com.toquete.boxbox.feature.home.ui.HomeRoute

fun NavGraphBuilder.homeScreen(
    onSettingsButtonClick: () -> Unit,
    builder: NavGraphBuilder.() -> Unit
) {
    composable<BoxBoxRoute.Home> {
        HomeRoute(
            onSettingsButtonClick = onSettingsButtonClick,
            builder = builder
        )
    }
}
