package com.toquete.boxbox.domain.raceresults.usecase

import com.toquete.boxbox.core.model.RaceResult
import com.toquete.boxbox.data.raceresults.repository.RaceResultRepository
import com.toquete.boxbox.domain.common.usecase.GetTodayLocalDateUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentSeasonRaceResultsUseCase @Inject constructor(
    private val raceResultRepository: RaceResultRepository,
    private val getTodayLocalDateUseCase: GetTodayLocalDateUseCase
) {

    operator fun invoke(): Flow<List<RaceResult>> {
        return raceResultRepository.getRaceResultsBySeason(season = getTodayLocalDateUseCase().year.toString())
    }
}
