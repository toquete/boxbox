package com.toquete.boxbox.domain.fulldriverstandings

import com.toquete.boxbox.data.fulldriverstandings.repository.FullDriverStandingsRepository
import javax.inject.Inject

class SyncFullDriverStandingsUseCase @Inject constructor(
    private val repository: FullDriverStandingsRepository
) {

    suspend operator fun invoke(): Boolean {
        return repository.sync()
    }
}