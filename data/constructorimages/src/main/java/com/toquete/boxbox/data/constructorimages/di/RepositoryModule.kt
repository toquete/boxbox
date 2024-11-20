package com.toquete.boxbox.data.constructorimages.di

import com.toquete.boxbox.data.constructorimages.repository.DefaultConstructorImageRepository
import com.toquete.boxbox.domain.repository.ConstructorImageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    fun bindsConstructorImageRepository(
        repository: DefaultConstructorImageRepository
    ): ConstructorImageRepository
}
