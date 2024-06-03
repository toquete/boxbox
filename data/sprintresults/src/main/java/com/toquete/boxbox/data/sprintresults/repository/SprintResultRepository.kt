package com.toquete.boxbox.data.sprintresults.repository

import com.toquete.boxbox.core.common.util.Syncable
import com.toquete.boxbox.core.model.RaceResult
import kotlinx.coroutines.flow.Flow

interface SprintResultRepository : Syncable {

    fun getSprintResultsBySeasonAndRound(season: String, round: Int): Flow<List<RaceResult>>
}
