package com.toquete.boxbox.domain.repository

import com.toquete.boxbox.core.common.util.Syncable
import com.toquete.boxbox.core.model.Race
import kotlinx.coroutines.flow.Flow

interface RaceRepository : Syncable {

    fun getUpcomingRacesBySeason(season: String, today: String): Flow<List<Race>>

    fun getPastRacesBySeason(season: String, today: String): Flow<List<Race>>
}
