package com.toquete.boxbox.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.toquete.boxbox.feature.home.ui.HomeRoute
import kotlinx.serialization.Serializable

@Serializable data object Home

fun NavGraphBuilder.homeScreen(
    onSettingsButtonClick: () -> Unit,
    builder: NavGraphBuilder.() -> Unit
) {
    composable<Home> {
        HomeRoute(
            onSettingsButtonClick = onSettingsButtonClick,
            builder = builder
        )
    }
}
