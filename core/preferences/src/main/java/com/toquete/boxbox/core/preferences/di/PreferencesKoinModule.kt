package com.toquete.boxbox.core.preferences.di

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import com.toquete.boxbox.core.preferences.repository.DataStoreUserPreferencesRepository
import com.toquete.boxbox.domain.repository.UserPreferencesRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

private const val USER_PREFERENCES = "user_preferences"

val preferencesModule = module {
    single {
        PreferenceDataStoreFactory.create(
            produceFile = { androidContext().preferencesDataStoreFile(USER_PREFERENCES) }
        )
    }
    singleOf(::DataStoreUserPreferencesRepository) bind UserPreferencesRepository::class
}
