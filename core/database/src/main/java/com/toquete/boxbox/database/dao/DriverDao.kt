package com.toquete.boxbox.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.toquete.boxbox.database.model.DriverEntity

@Dao
interface DriverDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(drivers: List<DriverEntity>)
}