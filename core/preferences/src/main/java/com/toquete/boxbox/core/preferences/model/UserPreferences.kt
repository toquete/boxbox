package com.toquete.boxbox.core.preferences.model

import com.toquete.boxbox.core.model.DarkThemeConfig

data class UserPreferences(
    val driverStandingsLastUpdatedTime: Long,
    val darkThemeConfig: DarkThemeConfig
)
