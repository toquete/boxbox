package com.toquete.boxbox.core.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
internal expect object BoxBoxDatabaseConstructor : RoomDatabaseConstructor<BoxBoxDatabase> {
    override fun initialize(): BoxBoxDatabase
}
