package com.toquete.boxbox.core.preferences.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.toquete.boxbox.core.model.ColorConfig
import com.toquete.boxbox.core.model.DarkThemeConfig
import com.toquete.boxbox.core.model.UserPreferences
import com.toquete.boxbox.core.preferences.PreferencesKeys.COLOR_CONFIG
import com.toquete.boxbox.core.preferences.PreferencesKeys.DARK_THEME_CONFIG
import com.toquete.boxbox.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class DefaultUserPreferencesRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : UserPreferencesRepository {

    override val userPreferences: Flow<UserPreferences> = dataStore.data.map { preferences ->
        val darkThemeConfig = preferences[DARK_THEME_CONFIG]?.let {
            DarkThemeConfig.entries[it]
        } ?: DarkThemeConfig.FOLLOW_SYSTEM
        val colorConfig = preferences[COLOR_CONFIG]?.let {
            ColorConfig.entries[it]
        } ?: ColorConfig.DEFAULT

        UserPreferences(
            darkThemeConfig = darkThemeConfig,
            colorConfig = colorConfig
        )
    }

    override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        dataStore.edit { preferences ->
            preferences[DARK_THEME_CONFIG] = darkThemeConfig.ordinal
        }
    }

    override suspend fun setColorConfig(colorConfig: ColorConfig) {
        dataStore.edit { preferences ->
            preferences[COLOR_CONFIG] = colorConfig.ordinal
        }
    }
}
