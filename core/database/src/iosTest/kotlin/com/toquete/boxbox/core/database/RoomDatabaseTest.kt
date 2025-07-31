package com.toquete.boxbox.core.database

import androidx.room.Room
import androidx.room.RoomDatabase

actual abstract class RoomDatabaseTest {
    actual fun getInMemoryDatabaseBuilder(): RoomDatabase.Builder<BoxBoxDatabase> {
        return Room.inMemoryDatabaseBuilder()
    }
}
