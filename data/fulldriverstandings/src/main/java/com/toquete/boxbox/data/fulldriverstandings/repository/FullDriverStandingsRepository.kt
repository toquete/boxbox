package com.toquete.boxbox.data.fulldriverstandings.repository

import com.toquete.boxbox.model.FullDriverStanding
import kotlinx.coroutines.flow.Flow

interface FullDriverStandingsRepository {

    fun getFullDriverStandings(): Flow<List<FullDriverStanding>>

    suspend fun sync(): Boolean
}