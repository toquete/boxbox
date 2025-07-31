package com.toquete.boxbox.domain.fake

import com.toquete.boxbox.core.model.RaceResult
import com.toquete.boxbox.domain.mock.sprintRaceResults
import com.toquete.boxbox.domain.repository.SprintResultRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeSprintResultRepository : SprintResultRepository {

    override fun getSprintResultsBySeasonAndRound(
        season: String,
        round: Int
    ): Flow<List<RaceResult>> {
        return flowOf(sprintRaceResults)
    }

    override suspend fun sync() = Unit
}
