package com.toquete.boxbox.feature.settings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog
import com.toquete.boxbox.core.navigation.SettingsRoute
import com.toquete.boxbox.feature.settings.ui.SettingsScreen

fun NavController.navigateToSettings() {
    navigate(SettingsRoute)
}

fun NavGraphBuilder.settingsScreen(onDismiss: () -> Unit = { }) {
    dialog<SettingsRoute> {
        SettingsScreen(onDismiss = onDismiss)
    }
}
