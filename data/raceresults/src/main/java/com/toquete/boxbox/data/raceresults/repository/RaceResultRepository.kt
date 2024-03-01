package com.toquete.boxbox.data.raceresults.repository

import com.toquete.boxbox.core.common.util.Syncable
import com.toquete.boxbox.core.model.RaceResult
import kotlinx.coroutines.flow.Flow

interface RaceResultRepository : Syncable {

    fun getRaceResultsBySeason(season: String): Flow<List<RaceResult>>
}
