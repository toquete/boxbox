package com.toquete.boxbox.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.toquete.boxbox.core.database.model.DriverImageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DriverImageDao {

    @Query("SELECT * FROM drivers_images WHERE id = :id")
    fun getDriverImageById(id: String): Flow<DriverImageEntity>

    @Insert
    suspend fun insertAll(driverImages: List<DriverImageEntity>)
}
