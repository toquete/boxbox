package com.toquete.boxbox.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.datetime.Clock

@Module
@InstallIn(SingletonComponent::class)
internal object CommonModule {

    @Provides
    fun provideClock(): Clock = Clock.System
}
