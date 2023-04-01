package com.toquete.boxbox.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.toquete.boxbox.database.model.DriverStandingEntity

@Dao
interface DriverStandingDao {

    @Query("SELECT * FROM driver_standings ORDER BY position")
    suspend fun getDriverStandings(): List<DriverStandingEntity>

    @Insert
    suspend fun insertAll(driverStandings: List<DriverStandingEntity>)

    @Query("DELETE FROM driver_standings")
    suspend fun deleteAll()
}