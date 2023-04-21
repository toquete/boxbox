package com.toquete.boxbox.domain.fullconstructorstandings

import com.toquete.boxbox.data.fullconstructorstandings.repository.FullConstructorStandingsRepository
import com.toquete.boxbox.model.FullConstructorStanding
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFullConstructorStandingsUseCase @Inject constructor(
    private val repository: FullConstructorStandingsRepository
) {

    operator fun invoke(): Flow<List<FullConstructorStanding>> {
        return repository.getFullConstructorStandings()
    }
}