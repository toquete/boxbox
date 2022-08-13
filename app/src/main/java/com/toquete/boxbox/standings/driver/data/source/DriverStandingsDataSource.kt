package com.toquete.boxbox.standings.driver.data.source

import com.toquete.boxbox.standings.driver.data.source.remote.model.DriverStandingsWrapper

interface DriverStandingsDataSource {

    suspend fun getDriverStandings(): DriverStandingsWrapper
}