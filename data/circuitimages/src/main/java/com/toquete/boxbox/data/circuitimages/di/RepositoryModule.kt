package com.toquete.boxbox.data.circuitimages.di

import com.toquete.boxbox.data.circuitimages.repository.DefaultCircuitImageRepository
import com.toquete.boxbox.domain.repository.CircuitImageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
internal fun interface RepositoryModule {

    @Binds
    @Named("internal")
    fun bindCircuitImageRepository(
        repository: DefaultCircuitImageRepository
    ): CircuitImageRepository
}
