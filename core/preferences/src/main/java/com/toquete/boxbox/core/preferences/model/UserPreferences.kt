package com.toquete.boxbox.core.preferences.model

import com.toquete.boxbox.core.model.ColorConfig
import com.toquete.boxbox.core.model.DarkThemeConfig

data class UserPreferences(
    val darkThemeConfig: DarkThemeConfig,
    val colorConfig: ColorConfig
)
