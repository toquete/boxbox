package com.toquete.boxbox.flags.data.source.remote

import com.toquete.boxbox.flags.data.source.FlagsDataSource
import com.toquete.boxbox.flags.data.source.remote.model.FlagsDataResponse
import com.toquete.boxbox.flags.data.source.remote.service.FlagsService
import com.toquete.boxbox.infrastructure.service.FlagsServiceFactory

class FlagsRemoteDataSource(
    private val service: FlagsService = FlagsServiceFactory.getBuilder().create(FlagsService::class.java)
) : FlagsDataSource {

    override suspend fun getFlagInfoByName(name: String): List<FlagsDataResponse> {
        return service.getFlagInfoByName(name)
    }
}