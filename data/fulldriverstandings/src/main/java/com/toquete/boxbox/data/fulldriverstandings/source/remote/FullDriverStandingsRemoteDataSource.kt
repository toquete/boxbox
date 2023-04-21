package com.toquete.boxbox.data.fulldriverstandings.source.remote

import com.toquete.boxbox.model.FullDriverStanding

internal interface FullDriverStandingsRemoteDataSource {

    suspend fun getFullDriverStandings(): List<FullDriverStanding>
}