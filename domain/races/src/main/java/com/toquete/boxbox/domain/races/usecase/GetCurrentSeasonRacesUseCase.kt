package com.toquete.boxbox.domain.races.usecase

import com.toquete.boxbox.core.model.Race
import com.toquete.boxbox.data.races.repository.RaceRepository
import com.toquete.boxbox.domain.common.usecase.GetTodayLocalDateUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentSeasonRacesUseCase @Inject constructor(
    private val raceRepository: RaceRepository,
    private val getTodayLocalDateUseCase: GetTodayLocalDateUseCase
) {

    operator fun invoke(): Flow<List<Race>> {
        return raceRepository.getRacesBySeason(season = getTodayLocalDateUseCase().year.toString())
    }
}
