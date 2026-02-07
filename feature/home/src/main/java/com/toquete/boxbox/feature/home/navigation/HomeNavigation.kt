package com.toquete.boxbox.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.toquete.boxbox.core.navigation.HomeRoute
import com.toquete.boxbox.feature.home.ui.HomeRoute as HomeScreen

fun NavGraphBuilder.homeScreen(
    onSettingsButtonClick: () -> Unit,
    builder: NavGraphBuilder.() -> Unit
) {
    composable<HomeRoute> {
        HomeScreen(
            onSettingsButtonClick = onSettingsButtonClick,
            builder = builder
        )
    }
}
