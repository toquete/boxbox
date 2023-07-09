package com.toquete.boxbox.di

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
    fun providesDebugTree(): Timber.Tree {
        return Timber.DebugTree()
    }
}
