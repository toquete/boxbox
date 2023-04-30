package com.toquete.boxbox.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.toquete.boxbox.core.database.model.DriverEntity

@Dao
interface DriverDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(drivers: List<DriverEntity>)
}