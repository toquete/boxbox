package com.toquete.boxbox.di

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.toquete.boxbox.util.log.CrashlyticsReportingTree
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TreeModule {

    @Provides
    @Singleton
    fun providesCrashlyticsReportingTree(crashlytics: FirebaseCrashlytics): Timber.Tree {
        return CrashlyticsReportingTree(crashlytics)
    }
}
