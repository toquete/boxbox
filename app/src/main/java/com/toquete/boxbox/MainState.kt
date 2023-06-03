package com.toquete.boxbox

import com.toquete.boxbox.core.model.DarkThemeConfig

data class MainState(
    val isOnline: Boolean = true,
    val isSyncing: Boolean = true,
    val hasFailed: Boolean = false,
    val darkThemeConfig: DarkThemeConfig = DarkThemeConfig.FOLLOW_SYSTEM
)
