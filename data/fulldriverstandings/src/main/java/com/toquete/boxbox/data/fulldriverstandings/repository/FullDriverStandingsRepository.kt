package com.toquete.boxbox.data.fulldriverstandings.repository

import com.toquete.boxbox.model.FullDriverStanding

interface FullDriverStandingsRepository {

    suspend fun getFullDriverStandings(): List<FullDriverStanding>
}