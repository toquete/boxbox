package com.toquete.boxbox.standings.driver.data.source

import com.toquete.boxbox.standings.driver.domain.model.DriverStanding
import kotlinx.coroutines.flow.Flow

interface DriverStandingsDataSource {

    fun getDriverStandings(): Flow<List<DriverStanding>>
}