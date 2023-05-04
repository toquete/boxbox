package com.toquete.boxbox.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.toquete.boxbox.core.database.model.DriverStandingEntity

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
}
