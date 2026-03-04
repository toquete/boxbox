package com.toquete.boxbox.feature.settings.ui

import com.toquete.boxbox.core.model.ColorConfig
import com.toquete.boxbox.core.model.DarkThemeConfig

sealed interface SettingsIntent {
    data class SelectTheme(val darkThemeConfig: DarkThemeConfig) : SettingsIntent
    data class SelectColor(val colorConfig: ColorConfig) : SettingsIntent
}