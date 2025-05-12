package com.toquete.boxbox.core.network

import com.toquete.boxbox.core.common.MAX_RESPONSE_LIMIT
import com.toquete.boxbox.core.network.model.ConstructorStandingsWrapper
import com.toquete.boxbox.core.network.model.DriverStandingsWrapper
import com.toquete.boxbox.core.network.model.RacesWrapper

interface BoxBoxService {

    suspend fun getDriverStandings(): DriverStandingsWrapper
    suspend fun getConstructorStandings(): ConstructorStandingsWrapper
    suspend fun getRaces(): RacesWrapper
    suspend fun getRaceResults(offset: Int, limit: Int = MAX_RESPONSE_LIMIT): RacesWrapper
    suspend fun getSprintRaceResults(offset: Int, limit: Int = MAX_RESPONSE_LIMIT): RacesWrapper
}
