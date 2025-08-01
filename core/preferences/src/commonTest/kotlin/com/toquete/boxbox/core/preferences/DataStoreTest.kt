package com.toquete.boxbox.core.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.CoroutineScope

internal const val TEST_USER_PREFERENCES = "user_preferences_test.preferences_pb"

expect abstract class DataStoreTest() {
    fun getInMemoryDataStore(scope: CoroutineScope): DataStore<Preferences>
}
