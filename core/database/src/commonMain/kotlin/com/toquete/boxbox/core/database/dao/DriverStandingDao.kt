package com.toquete.boxbox.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.toquete.boxbox.core.database.model.DriverStandingEntity
import com.toquete.boxbox.core.database.model.FullDriverStandingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DriverStandingDao {

    @Insert
    suspend fun insertAll(driverStandings: List<DriverStandingEntity>)

    @Query("DELETE FROM driver_standings")
    suspend fun deleteAll()

    @Transaction
    suspend fun deleteAndInsertAllInTransaction(driverStandings: List<DriverStandingEntity>) {
        deleteAll()
        insertAll(driverStandings)
    }

    @Query("SELECT * FROM driver_standings ORDER BY position ASC")
    fun getDriverStandings(): Flow<List<DriverStandingEntity>>

    @Transaction
    @Query("SELECT * FROM driver_standings ORDER BY position ASC")
    fun getFullDriverStandings(): Flow<List<FullDriverStandingEntity>>
}
