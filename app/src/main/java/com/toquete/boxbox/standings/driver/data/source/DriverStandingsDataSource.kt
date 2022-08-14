package com.toquete.boxbox.standings.driver.data.source

import com.toquete.boxbox.standings.driver.domain.model.DriverStanding

interface DriverStandingsDataSource {

    suspend fun getDriverStandings(): List<DriverStanding>
}