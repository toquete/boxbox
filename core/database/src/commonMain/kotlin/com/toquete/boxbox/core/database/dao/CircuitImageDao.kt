package com.toquete.boxbox.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.toquete.boxbox.core.database.model.CircuitImageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CircuitImageDao {

    @Query("SELECT * FROM circuits_images")
    fun getCircuitImages(): Flow<List<CircuitImageEntity>>

    @Query("SELECT * FROM circuits_images WHERE id =:id")
    fun getCircuitImageById(id: String): Flow<CircuitImageEntity>

    @Upsert
    suspend fun upsertAll(circuitImages: List<CircuitImageEntity>)
}
