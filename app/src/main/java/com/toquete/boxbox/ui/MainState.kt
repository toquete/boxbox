package com.toquete.boxbox.ui

import com.toquete.boxbox.core.model.ColorConfig
import com.toquete.boxbox.core.model.DarkThemeConfig

data class MainState(
    val isLoading: Boolean = true,
    val darkThemeConfig: DarkThemeConfig = DarkThemeConfig.FOLLOW_SYSTEM,
    val colorConfig: ColorConfig = ColorConfig.DEFAULT
)
