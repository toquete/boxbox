package com.toquete.boxbox.data.fulldriverstandings.source.local

import com.toquete.boxbox.model.FullDriverStanding

internal interface FullDriverStandingsLocalDataSource {

    suspend fun getFullDriverStandings(): List<FullDriverStanding>
}