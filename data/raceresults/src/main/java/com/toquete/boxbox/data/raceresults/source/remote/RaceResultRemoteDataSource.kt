package com.toquete.boxbox.data.raceresults.source.remote

import com.toquete.boxbox.core.network.model.RacesWrapper

internal fun interface RaceResultRemoteDataSource {

    suspend fun getRaceResults(offset: Int): RacesWrapper
}
