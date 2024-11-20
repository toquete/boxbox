package com.toquete.boxbox.data.circuitimages.di

import com.toquete.boxbox.data.circuitimages.repository.DefaultCircuitImageRepository
import com.toquete.boxbox.domain.repository.CircuitImageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    fun bindCircuitImageRepository(
        repository: DefaultCircuitImageRepository
    ): CircuitImageRepository
}
