package com.toquete.boxbox.domain.usecase

import com.toquete.boxbox.core.model.RaceResult
import com.toquete.boxbox.domain.repository.SprintResultRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentSeasonSprintResultsUseCase @Inject constructor(
    private val sprintResultRepository: SprintResultRepository,
    private val getTodayLocalDateUseCase: GetTodayLocalDateUseCase
) {

    operator fun invoke(round: Int): Flow<List<RaceResult>> {
        return sprintResultRepository.getSprintResultsBySeasonAndRound(
            season = getTodayLocalDateUseCase().year.toString(),
            round
        )
    }
}
