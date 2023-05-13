package com.toquete.boxbox.core.preferences.repository

import com.toquete.boxbox.core.preferences.model.UserPreferences
import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {

    val userPreferences: Flow<UserPreferences>
}
