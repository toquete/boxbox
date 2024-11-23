package com.toquete.boxbox.feature.settings.ui

import com.toquete.boxbox.core.model.UserPreferences

sealed interface SettingsState {
    data object Loading : SettingsState
    data class Success(val data: UserPreferences) : SettingsState
}
