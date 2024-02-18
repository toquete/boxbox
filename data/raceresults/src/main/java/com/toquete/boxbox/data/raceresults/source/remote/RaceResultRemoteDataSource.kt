package com.toquete.boxbox.data.raceresults.source.remote

import com.toquete.boxbox.core.network.model.RaceResponse

internal interface RaceResultRemoteDataSource {

    suspend fun getRaceResults(): List<RaceResponse>
}
