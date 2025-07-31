package com.toquete.boxbox.core.database

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import androidx.test.core.app.ApplicationProvider

actual abstract class RoomDatabaseTest {
    actual fun getInMemoryDatabaseBuilder(): RoomDatabase.Builder<BoxBoxDatabase> {
        return Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BoxBoxDatabase::class.java
        ).setDriver(BundledSQLiteDriver())
    }
}
