package com.toquete.boxbox.core.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.CoroutineScope
import org.junit.rules.TemporaryFolder

internal fun TemporaryFolder.testUserPreferencesDataStore(
    coroutineScope: CoroutineScope
) : DataStore<Preferences> {
    return PreferenceDataStoreFactory.create(scope = coroutineScope) {
        newFile("user_preferences_test.preferences_pb")
    }
}