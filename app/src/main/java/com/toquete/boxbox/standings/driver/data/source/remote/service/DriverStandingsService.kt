package com.toquete.boxbox.standings.driver.data.source.remote.service

import com.toquete.boxbox.standings.driver.data.source.remote.model.DriverStandingsWrapper
import retrofit2.http.GET

interface DriverStandingsService {

    @GET("current/driverStandings.json")
    suspend fun getDriverStandings(): DriverStandingsWrapper
}