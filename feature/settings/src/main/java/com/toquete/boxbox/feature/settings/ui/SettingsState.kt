package com.toquete.boxbox.feature.settings.ui

import com.toquete.boxbox.core.model.ColorConfig
import com.toquete.boxbox.core.model.DarkThemeConfig

internal data class SettingsState(
    val isLoading: Boolean = true,
    val darkThemeConfig: DarkThemeConfig = DarkThemeConfig.FOLLOW_SYSTEM,
    val colorConfig: ColorConfig = ColorConfig.DEFAULT,
    val lastUpdatedTime: String? = null
)
