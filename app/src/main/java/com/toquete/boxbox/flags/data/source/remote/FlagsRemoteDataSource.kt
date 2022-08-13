package com.toquete.boxbox.flags.data.source.remote

import com.toquete.boxbox.flags.data.source.FlagsDataSource
import com.toquete.boxbox.flags.data.source.remote.model.FlagsDataResponse
import com.toquete.boxbox.flags.data.source.remote.service.FlagsService
import com.toquete.boxbox.infrastructure.service.FlagsServiceFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FlagsRemoteDataSource(
    private val service: FlagsService = FlagsServiceFactory.getBuilder().create(FlagsService::class.java)
) : FlagsDataSource {

    override fun getFlagInfoByName(name: String): Flow<List<FlagsDataResponse>> = flow {
        emit(service.getFlagInfoByName(name))
    }
}