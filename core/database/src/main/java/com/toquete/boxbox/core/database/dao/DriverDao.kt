package com.toquete.boxbox.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.toquete.boxbox.core.database.model.DriverEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DriverDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(drivers: List<DriverEntity>)

    @Query("SELECT * FROM drivers")
    fun getDrivers(): Flow<List<DriverEntity>>
}
