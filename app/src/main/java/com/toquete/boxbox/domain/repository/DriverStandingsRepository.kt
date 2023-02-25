package com.toquete.boxbox.domain.repository

import com.toquete.boxbox.domain.model.DriverStanding

interface DriverStandingsRepository {

    suspend fun getDriverStandings(): List<DriverStanding>
}