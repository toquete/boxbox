package com.toquete.boxbox.data.fulldriverstandings.source.remote

import com.toquete.boxbox.network.model.DriverStandingResponse

internal interface FullDriverStandingsRemoteDataSource {

    suspend fun getFullDriverStandings(): List<DriverStandingResponse>
}