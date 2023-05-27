package com.toquete.boxbox.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.toquete.boxbox.core.database.model.DriverStandingEntity
import com.toquete.boxbox.core.database.model.NewFullDriverStandingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DriverStandingDao {

    @Insert
    suspend fun insertAll(driverStandings: List<DriverStandingEntity>)

    @Query("DELETE FROM driver_standings")
    suspend fun deleteAll()

    @Transaction
    suspend fun deleteAndInsertInTransaction(driverStandings: List<DriverStandingEntity>) {
        deleteAll()
        insertAll(driverStandings)
    }

    @Query("SELECT * FROM driver_standings")
    fun getDriverStandings(): Flow<List<DriverStandingEntity>>

    @Transaction
    @Query("SELECT * FROM driver_standings")
    fun getFullDriverStandings(): Flow<List<NewFullDriverStandingEntity>>
}
