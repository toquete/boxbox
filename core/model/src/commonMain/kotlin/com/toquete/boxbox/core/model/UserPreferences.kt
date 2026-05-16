package com.toquete.boxbox.core.model

data class UserPreferences(
    val darkThemeConfig: DarkThemeConfig,
    val colorConfig: ColorConfig,
    val lastUpdatedDateInMillis: Long?
)
