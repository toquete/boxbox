package com.toquete.boxbox.core.preferences.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.toquete.boxbox.core.model.DarkThemeConfig
import com.toquete.boxbox.core.preferences.PreferencesKeys.DARK_THEME_CONFIG
import com.toquete.boxbox.core.preferences.model.UserPreferences
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

        UserPreferences(
            darkThemeConfig = darkThemeConfig
        )
    }

    override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        dataStore.edit { preferences ->
            preferences[DARK_THEME_CONFIG] = darkThemeConfig.ordinal
        }
    }
}
