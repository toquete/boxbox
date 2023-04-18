package com.toquete.boxbox.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.toquete.boxbox.database.model.DriverEntity

@Dao
interface DriverDao {

    @Query("SELECT * FROM drivers WHERE id = :id")
    suspend fun getDriverById(id: String): DriverEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(drivers: List<DriverEntity>)
}