package com.toquete.boxbox.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.toquete.boxbox.core.database.model.DriverEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DriverDao {

    @Upsert
    suspend fun upsertAll(drivers: List<DriverEntity>)

    @Query("SELECT * FROM drivers")
    fun getDrivers(): Flow<List<DriverEntity>>
}
