package com.toquete.boxbox.core.database

import androidx.room.RoomDatabase

expect abstract class RoomDatabaseTest() {
    fun getInMemoryDatabaseBuilder(): RoomDatabase.Builder<BoxBoxDatabase>
}
