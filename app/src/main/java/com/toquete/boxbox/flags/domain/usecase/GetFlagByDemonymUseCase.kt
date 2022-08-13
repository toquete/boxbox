package com.toquete.boxbox.flags.domain.usecase

import com.toquete.boxbox.flags.data.repository.FlagsRepositoryImpl
import com.toquete.boxbox.flags.domain.model.Flag
import com.toquete.boxbox.flags.domain.repository.FlagsRepository
import kotlinx.coroutines.flow.Flow

class GetFlagByDemonymUseCase(
    private val repository: FlagsRepository = FlagsRepositoryImpl()
) {

    operator fun invoke(demonym: String): Flow<Flag> {
        return repository.getFlagByDemonym(demonym)
    }
}