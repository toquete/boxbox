package com.toquete.boxbox.core.preferences.di

import com.toquete.boxbox.core.preferences.repository.DataStoreUserPreferencesRepository
import com.toquete.boxbox.domain.repository.UserPreferencesRepository
import org.koin.dsl.module

internal val commonModule = module {
    single<UserPreferencesRepository> {
        DataStoreUserPreferencesRepository(dataStore = get())
    }
}

val preferencesModule = module {
    includes(commonModule, platformModule)
}
