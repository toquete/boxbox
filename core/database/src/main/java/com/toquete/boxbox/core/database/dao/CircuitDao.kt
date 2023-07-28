package com.toquete.boxbox.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.toquete.boxbox.core.database.model.CircuitEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CircuitDao {

    @Upsert
    suspend fun upsertAll(circuits: List<CircuitEntity>)

    @Query("SELECT * FROM circuits")
    fun getCircuits(): Flow<List<CircuitEntity>>
}
