package com.toquete.boxbox.data.circuitimages.di

import com.toquete.boxbox.data.circuitimages.repository.CircuitImageRepository
import com.toquete.boxbox.data.circuitimages.repository.DefaultCircuitImageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    abstract fun bindCircuitImageRepository(
        repository: DefaultCircuitImageRepository
    ): CircuitImageRepository
}
