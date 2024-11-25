package com.toquete.boxbox.fake

import com.toquete.boxbox.core.model.ColorConfig
import com.toquete.boxbox.core.model.DarkThemeConfig
import com.toquete.boxbox.core.model.UserPreferences
import com.toquete.boxbox.core.testing.data.preferences
import com.toquete.boxbox.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeUserPreferencesRepository : UserPreferencesRepository {

    override val userPreferences: Flow<UserPreferences>
        get() = flowOf(preferences)

    override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) = Unit

    override suspend fun setColorConfig(colorConfig: ColorConfig) = Unit
}
