package com.toquete.boxbox.domain.fulldriverstandings

import com.toquete.boxbox.data.fulldriverstandings.repository.FullDriverStandingsRepository
import com.toquete.boxbox.model.FullDriverStanding
import javax.inject.Inject

class GetFullDriverStandingsUseCase @Inject constructor(
    private val repository: FullDriverStandingsRepository
) {

    suspend operator fun invoke(): List<FullDriverStanding> {
        return repository.getFullDriverStandings()
    }
}