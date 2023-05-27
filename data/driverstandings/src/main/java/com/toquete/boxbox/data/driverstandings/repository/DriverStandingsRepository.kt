package com.toquete.boxbox.data.driverstandings.repository

import com.toquete.boxbox.core.common.Syncable
import com.toquete.boxbox.core.model.FullDriverStanding
import kotlinx.coroutines.flow.Flow

interface DriverStandingsRepository : Syncable {

    fun getDriverStandings(): Flow<List<FullDriverStanding>>
}
