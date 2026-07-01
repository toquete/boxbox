package com.toquete.boxbox.di

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.toquete.boxbox.util.log.CrashlyticsReportingTree
import org.koin.dsl.module
import timber.log.Timber

val treeModule = module {
    single<Timber.Tree> { CrashlyticsReportingTree(get<FirebaseCrashlytics>()) }
}
