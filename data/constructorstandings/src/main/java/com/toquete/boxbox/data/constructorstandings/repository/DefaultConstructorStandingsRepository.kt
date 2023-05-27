package com.toquete.boxbox.data.constructorstandings.repository

import com.toquete.boxbox.core.database.model.NewFullConstructorStandingEntity
import com.toquete.boxbox.core.model.FullConstructorStanding
import com.toquete.boxbox.core.network.model.ConstructorStandingResponse
import com.toquete.boxbox.data.constructorstandings.model.toDomain
import com.toquete.boxbox.data.constructorstandings.model.toEntity
import com.toquete.boxbox.data.constructorstandings.source.local.ConstructorStandingsLocalDataSource
import com.toquete.boxbox.data.constructorstandings.source.remote.ConstructorStandingsRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class DefaultConstructorStandingsRepository @Inject constructor(
    private val remoteDataSource: ConstructorStandingsRemoteDataSource,
    private val localDataSource: ConstructorStandingsLocalDataSource
) : ConstructorStandingsRepository {

    override fun getFullConstructorStandings(): Flow<List<FullConstructorStanding>> {
        return localDataSource.getConstructorStandings()
            .map { it.map(NewFullConstructorStandingEntity::toDomain) }
    }

    override suspend fun sync(): Boolean {
        return runCatching {
            remoteDataSource.getConstructorStandings()
                .also { list ->
                    localDataSource.insertAll(list.map(ConstructorStandingResponse::toEntity))
                }
        }.isSuccess
    }
}
