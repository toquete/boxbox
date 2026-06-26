package com.toquete.boxbox.core.network

import com.toquete.boxbox.core.common.MAX_RESPONSE_LIMIT
import com.toquete.boxbox.core.network.model.ConstructorStandingsWrapper
import com.toquete.boxbox.core.network.model.DriverStandingsWrapper
import com.toquete.boxbox.core.network.model.RacesWrapper

// TODO Step 12: delete — replaced by BoxBoxHttpClient (Ktor). Kept for :data:* Hilt injection.
interface BoxBoxService {

    suspend fun getDriverStandings(season: String = "current"): DriverStandingsWrapper

    suspend fun getConstructorStandings(season: String = "current"): ConstructorStandingsWrapper

    suspend fun getRaces(season: String = "current"): RacesWrapper

    suspend fun getRaceResults(
        season: String = "current",
        offset: Int,
        limit: Int = MAX_RESPONSE_LIMIT
    ): RacesWrapper

    suspend fun getSprintRaceResults(
        season: String = "current",
        offset: Int,
        limit: Int = MAX_RESPONSE_LIMIT
    ): RacesWrapper
}
