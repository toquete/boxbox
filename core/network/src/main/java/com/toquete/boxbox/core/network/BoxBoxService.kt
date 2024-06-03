package com.toquete.boxbox.core.network

import com.toquete.boxbox.core.network.model.ConstructorStandingsWrapper
import com.toquete.boxbox.core.network.model.DriverStandingsWrapper
import com.toquete.boxbox.core.network.model.RacesWrapper
import retrofit2.http.GET
import retrofit2.http.Query

private const val MAX_LIMIT = 1000

interface BoxBoxService {

    @GET("current/driverStandings.json")
    suspend fun getDriverStandings(): DriverStandingsWrapper

    @GET("current/constructorStandings.json")
    suspend fun getConstructorStandings(): ConstructorStandingsWrapper

    @GET("current.json")
    suspend fun getRaces(): RacesWrapper

    @GET("current/results.json")
    suspend fun getRaceResults(@Query("limit") limit: Int = MAX_LIMIT): RacesWrapper

    @GET("current/sprint.json")
    suspend fun getSprintRaceResults(@Query("limit") limit: Int = MAX_LIMIT): RacesWrapper
}
