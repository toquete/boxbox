package com.toquete.boxbox.standings.driver.domain.repository

import com.toquete.boxbox.standings.driver.domain.model.DriverStanding

interface DriverStandingsRepository {

    suspend fun getDriverStandings(): List<DriverStanding>
}