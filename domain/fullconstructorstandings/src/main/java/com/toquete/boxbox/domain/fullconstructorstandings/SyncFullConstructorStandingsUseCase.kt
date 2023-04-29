package com.toquete.boxbox.domain.fullconstructorstandings

import com.toquete.boxbox.data.fullconstructorstandings.repository.FullConstructorStandingsRepository
import javax.inject.Inject

class SyncFullConstructorStandingsUseCase @Inject constructor(
    private val repository: FullConstructorStandingsRepository
) {

    suspend operator fun invoke(): Boolean {
        return repository.sync()
    }
}