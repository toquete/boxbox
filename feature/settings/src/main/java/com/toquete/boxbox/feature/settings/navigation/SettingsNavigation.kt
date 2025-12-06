package com.toquete.boxbox.feature.settings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog
import com.toquete.boxbox.feature.settings.ui.SettingsScreen
import kotlinx.serialization.Serializable

@Serializable data object Settings

fun NavController.navigateToSettings() {
    navigate(Settings)
}

fun NavGraphBuilder.settingsScreen(onDismiss: () -> Unit = { }) {
    dialog<Settings> {
        SettingsScreen(onDismiss = onDismiss)
    }
}
