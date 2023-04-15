package com.toquete.boxbox.network

import com.toquete.boxbox.network.model.DriverStandingsWrapper
import retrofit2.http.GET

interface DriverStandingsService {

    @GET("current/driverStandings.json")
    suspend fun getDriverStandings(): DriverStandingsWrapper
}