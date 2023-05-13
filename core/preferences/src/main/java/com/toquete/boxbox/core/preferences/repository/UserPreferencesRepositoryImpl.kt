package com.toquete.boxbox.core.preferences.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.toquete.boxbox.core.model.DarkThemeConfig
import com.toquete.boxbox.core.preferences.PreferencesKeys.DARK_THEME_CONFIG
import com.toquete.boxbox.core.preferences.PreferencesKeys.DRIVER_STANDINGS_LAST_UPDATED_TIME
import com.toquete.boxbox.core.preferences.model.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock
import javax.inject.Inject

internal class UserPreferencesRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : UserPreferencesRepository {

    override val userPreferences: Flow<UserPreferences> = dataStore.data.map { preferences ->
        val driverStandingsLastUpdatedTime = preferences[DRIVER_STANDINGS_LAST_UPDATED_TIME]
            ?: Clock.System.now().toEpochMilliseconds()
        val darkThemeConfig = preferences[DARK_THEME_CONFIG]?.let {
            DarkThemeConfig.values()[it]
        } ?: DarkThemeConfig.FOLLOW_SYSTEM

        UserPreferences(
            driverStandingsLastUpdatedTime = driverStandingsLastUpdatedTime,
            darkThemeConfig = darkThemeConfig
        )
    }

    override suspend fun setDriverStandingsLastUpdatedTime(time: Long) {
        dataStore.edit { preferences ->
            preferences[DRIVER_STANDINGS_LAST_UPDATED_TIME] = time
        }
    }
}
