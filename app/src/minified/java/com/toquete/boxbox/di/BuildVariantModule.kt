package com.toquete.boxbox.di

import com.google.firebase.appcheck.AppCheckProviderFactory
import com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory
import org.koin.dsl.module
import timber.log.Timber

val buildVariantModule = module {
    single<AppCheckProviderFactory> { DebugAppCheckProviderFactory.getInstance() }
    single<Timber.Tree> { Timber.DebugTree() }
}
