package com.toquete.boxox.data.driverstandings.source.remote.service

import com.toquete.boxox.data.driverstandings.source.remote.model.DriverStandingsWrapper
import retrofit2.http.GET

internal interface DriverStandingsService {

    @GET("current/driverStandings.json")
    suspend fun getDriverStandings(): DriverStandingsWrapper
}