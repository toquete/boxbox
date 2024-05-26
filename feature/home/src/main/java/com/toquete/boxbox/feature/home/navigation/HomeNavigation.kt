package com.toquete.boxbox.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.toquete.boxbox.feature.home.ui.HomeScreen

const val HOME_ROUTE = "home"

fun NavGraphBuilder.homeScreen(
    isSyncing: Boolean,
    routes: List<String>,
    builder: NavGraphBuilder.() -> Unit
) {
    composable(route = HOME_ROUTE) {
        HomeScreen(
            isSyncing = isSyncing,
            routes = routes,
            builder = builder
        )
    }
}
