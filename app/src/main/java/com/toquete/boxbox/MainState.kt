package com.toquete.boxbox

import com.toquete.boxbox.core.model.DarkThemeConfig

data class MainState(
    val isLoading: Boolean = true,
    val darkThemeConfig: DarkThemeConfig = DarkThemeConfig.FOLLOW_SYSTEM
)
