package com.toquete.boxbox.fake

import com.toquete.boxbox.core.model.RaceResult
import com.toquete.boxbox.core.testing.data.raceResults
import com.toquete.boxbox.domain.repository.SprintResultRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeSprintResultRepository : SprintResultRepository {
    override fun getSprintResultsBySeasonAndRound(
        season: String,
        round: Int
    ): Flow<List<RaceResult>> {
        return flowOf(raceResults)
    }

    override suspend fun sync() = Unit
}
