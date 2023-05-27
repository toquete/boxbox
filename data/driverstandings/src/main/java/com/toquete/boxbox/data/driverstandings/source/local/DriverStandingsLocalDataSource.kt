package com.toquete.boxbox.data.driverstandings.source.local

import com.toquete.boxbox.core.database.model.DriverStandingEntity
import com.toquete.boxbox.core.database.model.NewFullDriverStandingEntity
import kotlinx.coroutines.flow.Flow

interface DriverStandingsLocalDataSource {

    suspend fun insertAll(driverStandings: List<DriverStandingEntity>)

    fun getDriverStandings(): Flow<List<NewFullDriverStandingEntity>>
}
