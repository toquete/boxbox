package com.toquete.boxbox.preferences.repository

import com.toquete.boxbox.preferences.model.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Instant

interface UserPreferencesRepository {

    val userPreferences: Flow<UserPreferences>

    suspend fun setDriverStandingsLastUpdatedTime(time: Instant)
}