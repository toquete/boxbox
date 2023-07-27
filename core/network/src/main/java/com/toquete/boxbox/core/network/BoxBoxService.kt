package com.toquete.boxbox.core.network

import com.toquete.boxbox.core.network.model.ConstructorStandingsWrapper
import com.toquete.boxbox.core.network.model.DriverStandingsWrapper
import com.toquete.boxbox.core.network.model.RacesWrapper
import retrofit2.http.GET

interface BoxBoxService {

    @GET("current/driverStandings.json")
    suspend fun getDriverStandings(): DriverStandingsWrapper

    @GET("current/constructorStandings.json")
    suspend fun getConstructorStandings(): ConstructorStandingsWrapper

    @GET("current.json")
    suspend fun getRaces(): RacesWrapper
}
