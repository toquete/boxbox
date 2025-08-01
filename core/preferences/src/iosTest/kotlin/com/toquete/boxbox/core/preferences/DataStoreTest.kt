package com.toquete.boxbox.core.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.CoroutineScope
import okio.Path.Companion.toPath
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

@OptIn(ExperimentalForeignApi::class)
actual abstract class DataStoreTest actual constructor() {
    actual fun getInMemoryDataStore(scope: CoroutineScope): DataStore<Preferences> {
        return PreferenceDataStoreFactory.createWithPath(scope = scope) {
            val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
                directory = NSDocumentDirectory,
                inDomain = NSUserDomainMask,
                appropriateForURL = null,
                create = false,
                error = null,
            )
            val filePath = documentDirectory?.path + TEST_USER_PREFERENCES
            filePath.toPath()
        }
    }
}
