package com.toquete.boxbox.core.database.di

import android.content.Context
import androidx.room.Room
import com.toquete.boxbox.core.database.BoxBoxDatabase
import com.toquete.boxbox.core.database.MIGRATION_5_6
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATABASE = "boxbox_database"

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BoxBoxDatabase {
        return Room.databaseBuilder(context, BoxBoxDatabase::class.java, DATABASE)
            .addMigrations(MIGRATION_5_6)
            .build()
    }
}
