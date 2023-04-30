package com.toquete.boxbox.data.fulldriverstandings.repository

import com.toquete.boxbox.core.common.Syncable
import com.toquete.boxbox.core.model.FullDriverStanding
import kotlinx.coroutines.flow.Flow

interface FullDriverStandingsRepository : Syncable {

    fun getFullDriverStandings(): Flow<List<FullDriverStanding>>
}