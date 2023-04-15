package com.toquete.boxbox.network

import com.toquete.boxbox.network.model.ConstructorStandingsWrapper
import com.toquete.boxbox.network.model.DriverStandingsWrapper
import retrofit2.http.GET

interface BoxBoxService {

    @GET("current/driverStandings.json")
    suspend fun getDriverStandings(): DriverStandingsWrapper

    @GET("current/constructorStandings.json")
    suspend fun getConstructorStandings(): ConstructorStandingsWrapper
}