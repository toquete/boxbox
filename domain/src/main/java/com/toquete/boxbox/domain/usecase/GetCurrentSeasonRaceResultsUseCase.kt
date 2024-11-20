package com.toquete.boxbox.domain.usecase

import com.toquete.boxbox.core.model.RaceResult
import com.toquete.boxbox.domain.repository.RaceResultRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentSeasonRaceResultsUseCase @Inject constructor(
    private val raceResultRepository: RaceResultRepository,
    private val getTodayLocalDateUseCase: GetTodayLocalDateUseCase
) {

    operator fun invoke(round: Int): Flow<List<RaceResult>> {
        return raceResultRepository.getRaceResultsBySeasonAndRound(
            season = getTodayLocalDateUseCase().year.toString(),
            round
        )
    }
}
