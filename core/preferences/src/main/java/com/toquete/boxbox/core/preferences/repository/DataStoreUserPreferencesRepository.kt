package com.toquete.boxbox.core.preferences.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.toquete.boxbox.core.model.ColorConfig
import com.toquete.boxbox.core.model.DarkThemeConfig
import com.toquete.boxbox.core.model.UserPreferences
import com.toquete.boxbox.core.preferences.PreferencesKeys.COLOR_CONFIG
import com.toquete.boxbox.core.preferences.PreferencesKeys.DARK_THEME_CONFIG
import com.toquete.boxbox.core.preferences.PreferencesKeys.LAST_UPDATED_DATE_IN_MILLIS
import com.toquete.boxbox.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class DataStoreUserPreferencesRepository(
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
            colorConfig = colorConfig,
            lastUpdatedDateInMillis = preferences[LAST_UPDATED_DATE_IN_MILLIS]
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

    override suspend fun setLastUpdatedDateInMillis(date: Long) {
        dataStore.edit { preferences ->
            preferences[LAST_UPDATED_DATE_IN_MILLIS] = date
        }
    }
}
