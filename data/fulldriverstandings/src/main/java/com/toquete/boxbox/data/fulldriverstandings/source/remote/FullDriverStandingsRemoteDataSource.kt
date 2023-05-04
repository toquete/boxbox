package com.toquete.boxbox.data.fulldriverstandings.source.remote

import com.toquete.boxbox.core.network.model.DriverStandingResponse

internal interface FullDriverStandingsRemoteDataSource {

    suspend fun getFullDriverStandings(): List<DriverStandingResponse>
}
