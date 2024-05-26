package com.toquete.boxbox.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.toquete.boxbox.feature.home.ui.HomeScreen

const val HOME_ROUTE = "home"

fun NavGraphBuilder.homeScreen(
    isOffline: Boolean,
    isSyncing: Boolean,
    onSettingsButtonClick: () -> Unit,
    builder: NavGraphBuilder.() -> Unit
) {
    composable(route = HOME_ROUTE) {
        HomeScreen(
            isOffline = isOffline,
            isSyncing = isSyncing,
            onSettingsButtonClick = onSettingsButtonClick,
            builder = builder
        )
    }
}
