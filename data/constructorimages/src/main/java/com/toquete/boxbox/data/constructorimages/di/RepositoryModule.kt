package com.toquete.boxbox.data.constructorimages.di

import com.toquete.boxbox.data.constructorimages.repository.DefaultConstructorImageRepository
import com.toquete.boxbox.domain.repository.ConstructorImageRepository
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
    fun bindsConstructorImageRepository(
        repository: DefaultConstructorImageRepository
    ): ConstructorImageRepository
}
