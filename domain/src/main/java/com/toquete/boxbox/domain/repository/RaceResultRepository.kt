package com.toquete.boxbox.domain.repository

import com.toquete.boxbox.core.common.util.Syncable
import com.toquete.boxbox.core.model.RaceResult
import kotlinx.coroutines.flow.Flow

interface RaceResultRepository : Syncable {

    fun getRaceResultsBySeasonAndRound(season: String, round: Int): Flow<List<RaceResult>>
}
