package com.toquete.boxbox.flags.data.repository

import com.toquete.boxbox.flags.data.source.FlagsDataSource
import com.toquete.boxbox.flags.data.source.remote.FlagsRemoteDataSource
import com.toquete.boxbox.flags.domain.model.Flag
import com.toquete.boxbox.flags.domain.repository.FlagsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FlagsRepositoryImpl(
    private val dataSource: FlagsDataSource = FlagsRemoteDataSource(),
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : FlagsRepository {

    private val demonymRange = 0..2

    override suspend fun getFlagByDemonym(demonym: String) : Flag {
        return withContext(dispatcher){
            dataSource.getFlagInfoByName(demonym.substring(demonymRange))
                .first { it.demonym.englishDemonym.masculine == demonym }
                .run {
                    Flag(
                        png = flagsResponse.png,
                        svg = flagsResponse.svg
                    )
                }
        }
    }
}