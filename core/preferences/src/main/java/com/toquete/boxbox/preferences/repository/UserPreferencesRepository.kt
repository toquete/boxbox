package com.toquete.boxbox.preferences.repository

import com.toquete.boxbox.preferences.model.UserPreferences
import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {

    val userPreferences: Flow<UserPreferences>

    suspend fun setDriverStandingsLastUpdatedTime(time: Long)
}