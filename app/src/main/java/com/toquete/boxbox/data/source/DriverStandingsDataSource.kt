package com.toquete.boxbox.data.source

import com.toquete.boxbox.domain.model.DriverStanding

interface DriverStandingsDataSource {

    suspend fun getDriverStandings(): List<DriverStanding>
}