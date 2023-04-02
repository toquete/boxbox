package com.toquete.boxbox.preferences.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.toquete.boxbox.preferences.PreferencesKeys.DRIVER_STANDINGS_LAST_UPDATED_TIME
import com.toquete.boxbox.preferences.model.UserPreferences
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

        UserPreferences(
            driverStandingsLastUpdatedTime = driverStandingsLastUpdatedTime
        )
    }

    override suspend fun setDriverStandingsLastUpdatedTime(time: Long) {
        dataStore.edit { preferences ->
            preferences[DRIVER_STANDINGS_LAST_UPDATED_TIME] = time
        }
    }
}