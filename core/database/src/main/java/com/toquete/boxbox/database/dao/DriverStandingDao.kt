package com.toquete.boxbox.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.toquete.boxbox.database.model.DriverStandingEntity

@Dao
interface DriverStandingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(driverStandings: List<DriverStandingEntity>)
}