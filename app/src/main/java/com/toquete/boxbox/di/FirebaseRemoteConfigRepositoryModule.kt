package com.toquete.boxbox.di

import com.toquete.boxbox.domain.repository.RemoteConfigRepository
import com.toquete.boxbox.util.remoteconfig.FirebaseRemoteConfigRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
fun interface FirebaseRemoteConfigRepositoryModule {

    @Binds
    fun bindRemoteConfigRepository(
        firebaseRemoteConfigRepository: FirebaseRemoteConfigRepository
    ): RemoteConfigRepository
}
