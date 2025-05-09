package com.toquete.boxbox.domain.usecase

import com.toquete.boxbox.core.model.Race
import com.toquete.boxbox.domain.repository.RaceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUpcomingRacesInCurrentSeasonUseCase @Inject constructor(
    private val repository: RaceRepository,
    private val getTodayLocalDateUseCase: GetTodayLocalDateUseCase
) {

    operator fun invoke(): Flow<List<Race>> {
        return repository.getUpcomingRacesBySeason(
            season = getTodayLocalDateUseCase().year.toString(),
            today = getTodayLocalDateUseCase().toString()
        )
    }
}
