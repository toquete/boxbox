package com.toquete.boxbox.standings.driver.domain.repository

import com.toquete.boxbox.standings.driver.domain.model.DriverStanding
import kotlinx.coroutines.flow.Flow

interface DriverStandingsRepository {

    fun getDriverStandings(): Flow<List<DriverStanding>>
}