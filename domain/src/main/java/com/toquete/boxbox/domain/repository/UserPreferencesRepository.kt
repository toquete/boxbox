package com.toquete.boxbox.domain.repository

import com.toquete.boxbox.core.model.ColorConfig
import com.toquete.boxbox.core.model.DarkThemeConfig
import com.toquete.boxbox.core.model.UserPreferences
import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {

    val userPreferences: Flow<UserPreferences>

    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig)
    suspend fun setColorConfig(colorConfig: ColorConfig)
}
