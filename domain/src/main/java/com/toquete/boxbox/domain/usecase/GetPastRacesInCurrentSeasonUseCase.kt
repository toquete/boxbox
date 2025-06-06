package com.toquete.boxbox.domain.usecase

import com.toquete.boxbox.core.model.Race
import com.toquete.boxbox.domain.repository.RaceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPastRacesInCurrentSeasonUseCase @Inject constructor(
    private val repository: RaceRepository,
    private val getTodayLocalDateUseCase: GetTodayLocalDateUseCase
) {

    operator fun invoke(): Flow<List<Race>> {
        return repository.getPastRacesBySeason(
            season = getTodayLocalDateUseCase().year.toString(),
            today = getTodayLocalDateUseCase().toString()
        )
    }
}
