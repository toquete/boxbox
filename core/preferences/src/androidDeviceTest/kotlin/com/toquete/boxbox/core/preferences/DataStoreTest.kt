package com.toquete.boxbox.core.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.CoroutineScope
import java.io.File

actual abstract class DataStoreTest actual constructor() {
    actual fun getInMemoryDataStore(scope: CoroutineScope): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(scope = scope) {
            File.createTempFile("user_preferences_test", ".preferences_pb").apply {
                deleteOnExit()
            }
        }
    }
}
