package com.toquete.boxbox.feature.settings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog
import com.toquete.boxbox.feature.settings.ui.SettingsScreen

const val SETTINGS_ROUTE = "settings"

fun NavController.navigateToSettings() {
    navigate(SETTINGS_ROUTE)
}

fun NavGraphBuilder.settingsScreen(onDismiss: () -> Unit = { }) {
    dialog(route = SETTINGS_ROUTE) {
        SettingsScreen(onDismiss = onDismiss)
    }
}
