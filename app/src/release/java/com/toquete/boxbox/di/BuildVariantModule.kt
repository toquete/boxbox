package com.toquete.boxbox.di

import com.google.firebase.appcheck.AppCheckProviderFactory
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory
import com.toquete.boxbox.util.log.CrashlyticsReportingTree
import org.koin.dsl.module
import timber.log.Timber

val buildVariantModule = module {
    single<AppCheckProviderFactory> { PlayIntegrityAppCheckProviderFactory.getInstance() }
    single<Timber.Tree> { CrashlyticsReportingTree(crashlytics = get()) }
}
