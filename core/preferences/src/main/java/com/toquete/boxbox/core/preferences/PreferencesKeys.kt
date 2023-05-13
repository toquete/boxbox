package com.toquete.boxbox.core.preferences

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey

internal object PreferencesKeys {
    val DRIVER_STANDINGS_LAST_UPDATED_TIME = longPreferencesKey("driver_standings_last_updated_time")
    val DARK_THEME_CONFIG = intPreferencesKey("dark_theme_config")
}
