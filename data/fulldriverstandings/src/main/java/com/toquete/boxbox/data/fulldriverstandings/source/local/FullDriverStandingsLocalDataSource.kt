package com.toquete.boxbox.data.fulldriverstandings.source.local

import com.toquete.boxbox.database.model.FullDriverStandingEntity

internal interface FullDriverStandingsLocalDataSource {

    suspend fun getFullDriverStandings(): List<FullDriverStandingEntity>
}