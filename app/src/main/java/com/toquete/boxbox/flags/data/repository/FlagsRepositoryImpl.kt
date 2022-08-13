package com.toquete.boxbox.flags.data.repository

import com.toquete.boxbox.flags.data.source.FlagsDataSource
import com.toquete.boxbox.flags.data.source.remote.FlagsRemoteDataSource
import com.toquete.boxbox.flags.domain.model.Flag
import com.toquete.boxbox.flags.domain.repository.FlagsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class FlagsRepositoryImpl(
    private val dataSource: FlagsDataSource = FlagsRemoteDataSource(),
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : FlagsRepository {

    private val demonymRange = 0..2

    override fun getFlagByDemonym(demonym: String): Flow<Flag> {
        return dataSource.getFlagInfoByName(demonym.substring(demonymRange))
            .map { data ->
                data.first { it.demonym.englishDemonym.masculine == demonym }
                    .run {
                        Flag(
                            png = flagsResponse.png,
                            svg = flagsResponse.svg
                        )
                    }
            }
            .flowOn(dispatcher)
    }
}