package com.toquete.boxbox.core.network

import com.toquete.boxbox.core.common.MAX_RESPONSE_LIMIT
import com.toquete.boxbox.core.network.model.ConstructorStandingsWrapper
import com.toquete.boxbox.core.network.model.DriverStandingsWrapper
import com.toquete.boxbox.core.network.model.RacesWrapper
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// TODO Step 12: delete — Retrofit-annotated interface backing BoxBoxService shim until Ktor.
interface BoxBoxRetrofitService {

    @GET("{season}/driverStandings.json")
    suspend fun getDriverStandings(
        @Path("season") season: String = "current"
    ): DriverStandingsWrapper

    @GET("{season}/constructorStandings.json")
    suspend fun getConstructorStandings(
        @Path("season") season: String = "current"
    ): ConstructorStandingsWrapper

    @GET("{season}/races.json")
    suspend fun getRaces(
        @Path("season") season: String = "current"
    ): RacesWrapper

    @GET("{season}/results.json")
    suspend fun getRaceResults(
        @Path("season") season: String = "current",
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = MAX_RESPONSE_LIMIT
    ): RacesWrapper

    @GET("{season}/sprint.json")
    suspend fun getSprintRaceResults(
        @Path("season") season: String = "current",
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = MAX_RESPONSE_LIMIT
    ): RacesWrapper
}

fun BoxBoxRetrofitService.asBoxBoxService(): BoxBoxService = object : BoxBoxService {
    override suspend fun getDriverStandings(season: String) =
        this@asBoxBoxService.getDriverStandings(season)
    override suspend fun getConstructorStandings(season: String) =
        this@asBoxBoxService.getConstructorStandings(season)
    override suspend fun getRaces(season: String) =
        this@asBoxBoxService.getRaces(season)
    override suspend fun getRaceResults(season: String, offset: Int, limit: Int) =
        this@asBoxBoxService.getRaceResults(season, offset, limit)
    override suspend fun getSprintRaceResults(season: String, offset: Int, limit: Int) =
        this@asBoxBoxService.getSprintRaceResults(season, offset, limit)
}
