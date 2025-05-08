package com.toquete.boxbox.core.preferences.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.toquete.boxbox.core.preferences.repository.DataStoreUserPreferencesRepository
import com.toquete.boxbox.domain.repository.UserPreferencesRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val USER_PREFERENCES = "user_preferences"

val preferencesModule = module {
    single<DataStore<Preferences>> {
        PreferenceDataStoreFactory.create(
            produceFile = { androidContext().preferencesDataStoreFile(USER_PREFERENCES) }
        )
    }
    single<UserPreferencesRepository> {
        DataStoreUserPreferencesRepository(dataStore = get())
    }
}
