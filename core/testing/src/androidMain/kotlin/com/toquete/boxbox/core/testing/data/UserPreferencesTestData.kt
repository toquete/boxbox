package com.toquete.boxbox.core.testing.data

import com.toquete.boxbox.core.model.ColorConfig
import com.toquete.boxbox.core.model.DarkThemeConfig
import com.toquete.boxbox.core.model.UserPreferences

val preferences = UserPreferences(
    darkThemeConfig = DarkThemeConfig.FOLLOW_SYSTEM,
    colorConfig = ColorConfig.DEFAULT,
    lastUpdatedDateInMillis = 1704121200000
)
