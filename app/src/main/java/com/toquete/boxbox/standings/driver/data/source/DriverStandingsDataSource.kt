package com.toquete.boxbox.standings.driver.data.source

import com.toquete.boxbox.standings.driver.data.source.remote.model.DriverStandingsWrapper
import kotlinx.coroutines.flow.Flow

interface DriverStandingsDataSource {

    fun getDriverStandings(): Flow<DriverStandingsWrapper>
}