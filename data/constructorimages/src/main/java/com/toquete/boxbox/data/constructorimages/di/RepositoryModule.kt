package com.toquete.boxbox.data.constructorimages.di

import com.toquete.boxbox.data.constructorimages.repository.ConstructorImageRepository
import com.toquete.boxbox.data.constructorimages.repository.DefaultConstructorImageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    abstract fun bindsConstructorImageRepository(
        repository: DefaultConstructorImageRepository
    ): ConstructorImageRepository
}
