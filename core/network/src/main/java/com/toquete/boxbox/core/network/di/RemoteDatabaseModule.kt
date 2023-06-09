package com.toquete.boxbox.core.network.di

import com.toquete.boxbox.core.network.BoxBoxRemoteDatabase
import com.toquete.boxbox.core.network.firebase.FirebaseDatabase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RemoteDatabaseModule {

    @Binds
    @Singleton
    abstract fun bindsBoxBoxRemoteDatabase(
        database: FirebaseDatabase
    ): BoxBoxRemoteDatabase
}
