package com.toquete.boxbox.domain.fulldriverstandings

import com.toquete.boxbox.core.model.FullDriverStanding
import com.toquete.boxbox.data.fulldriverstandings.repository.FullDriverStandingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFullDriverStandingsUseCase @Inject constructor(
    private val repository: FullDriverStandingsRepository
) {

    operator fun invoke(): Flow<List<FullDriverStanding>> {
        return repository.getFullDriverStandings()
    }
}