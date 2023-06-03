package com.toquete.boxbox.data.driverstandings.repository

import com.toquete.boxbox.core.common.util.Syncable
import com.toquete.boxbox.core.model.DriverStanding
import kotlinx.coroutines.flow.Flow

interface DriverStandingsRepository : Syncable {

    fun getDriverStandings(): Flow<List<DriverStanding>>
}
