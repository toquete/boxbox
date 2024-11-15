package com.toquete.boxbox.data.constructorstandings.repository

import com.toquete.boxbox.core.common.annotation.IoDispatcher
import com.toquete.boxbox.core.database.model.FullConstructorStandingEntity
import com.toquete.boxbox.core.model.ConstructorStanding
import com.toquete.boxbox.core.network.model.ConstructorStandingResponse
import com.toquete.boxbox.data.constructorstandings.model.toDomain
import com.toquete.boxbox.data.constructorstandings.model.toEntity
import com.toquete.boxbox.data.constructorstandings.source.local.ConstructorStandingsLocalDataSource
import com.toquete.boxbox.data.constructorstandings.source.remote.ConstructorStandingsRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class DefaultConstructorStandingsRepository @Inject constructor(
    private val remoteDataSource: ConstructorStandingsRemoteDataSource,
    private val localDataSource: ConstructorStandingsLocalDataSource,
    @IoDispatcher private val dispatcher: CoroutineContext
) : ConstructorStandingsRepository {

    override fun getConstructorStandings(): Flow<List<ConstructorStanding>> {
        return localDataSource.getConstructorStandings()
            .map { it.map(FullConstructorStandingEntity::toDomain) }
            .flowOn(dispatcher)
    }

    override suspend fun sync() {
        withContext(dispatcher) {
            val list = remoteDataSource.getConstructorStandings()
            localDataSource.insertAll(list.map(ConstructorStandingResponse::toEntity))
        }
    }
}
