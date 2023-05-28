package com.toquete.boxbox.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.toquete.boxbox.core.database.model.DriverStandingEntity
import com.toquete.boxbox.core.database.model.FullDriverStandingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DriverStandingDao {

    @Upsert
    suspend fun upsertAll(driverStandings: List<DriverStandingEntity>)

    @Query("DELETE FROM driver_standings")
    suspend fun deleteAll()

    @Query("SELECT * FROM driver_standings ORDER BY position ASC")
    fun getDriverStandings(): Flow<List<DriverStandingEntity>>

    @Transaction
    @Query("SELECT * FROM driver_standings ORDER BY position ASC")
    fun getFullDriverStandings(): Flow<List<FullDriverStandingEntity>>
}
