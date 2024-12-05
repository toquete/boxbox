package com.toquete.boxbox.core.preferences

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey

internal object PreferencesKeys {
    val DARK_THEME_CONFIG = intPreferencesKey("dark_theme_config")
    val COLOR_CONFIG = intPreferencesKey("color_config")
    val LAST_UPDATED_DATE_IN_MILLIS = longPreferencesKey("last_updated_date_in_millis")
}
