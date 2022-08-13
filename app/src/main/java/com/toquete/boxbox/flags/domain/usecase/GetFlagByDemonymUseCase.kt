package com.toquete.boxbox.flags.domain.usecase

import com.toquete.boxbox.flags.data.repository.FlagsRepositoryImpl
import com.toquete.boxbox.flags.domain.model.Flag
import com.toquete.boxbox.flags.domain.repository.FlagsRepository

class GetFlagByDemonymUseCase(
    private val repository: FlagsRepository = FlagsRepositoryImpl()
) {

    suspend operator fun invoke(demonym: String): Flag {
        return repository.getFlagByDemonym(demonym)
    }
}