package com.toquete.boxbox.domain.constructorstandings

import com.toquete.boxbox.data.constructorstandings.repository.ConstructorStandingsRepository
import com.toquete.boxbox.model.ConstructorStanding
import javax.inject.Inject

class GetConstructorStandingsUseCase @Inject constructor(
    private val repository: ConstructorStandingsRepository
) {

    suspend operator fun invoke(): List<ConstructorStanding> {
        return repository.getConstructorStandings()
    }
}