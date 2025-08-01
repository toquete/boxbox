package com.toquete.boxbox.core.preferences.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.toquete.boxbox.core.preferences.USER_PREFERENCES
import com.toquete.boxbox.core.preferences.createDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val platformModule: Module = module {
    single<DataStore<Preferences>> {
        createDataStore(
            producePath = { androidContext().filesDir.resolve(USER_PREFERENCES).absolutePath }
        )
    }
}
