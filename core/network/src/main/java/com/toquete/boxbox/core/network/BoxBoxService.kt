package com.toquete.boxbox.core.network

import com.toquete.boxbox.core.common.MAX_RESPONSE_LIMIT
import com.toquete.boxbox.core.network.model.ConstructorStandingsWrapper
import com.toquete.boxbox.core.network.model.DriverStandingsWrapper
import com.toquete.boxbox.core.network.model.RacesWrapper
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BoxBoxService {

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

    @GET("current/sprint.json")
    suspend fun getSprintRaceResults(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = MAX_RESPONSE_LIMIT
    ): RacesWrapper
}
