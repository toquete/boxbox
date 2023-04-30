package com.toquete.boxbox.data.fullconstructorstandings.repository

import com.toquete.boxbox.core.database.model.FullConstructorStandingEntity
import com.toquete.boxbox.core.model.FullConstructorStanding
import com.toquete.boxbox.core.network.model.ConstructorStandingResponse
import com.toquete.boxbox.data.constructorstandings.source.local.ConstructorStandingsLocalDataSource
import com.toquete.boxbox.data.fullconstructorstandings.model.toDomain
import com.toquete.boxbox.data.fullconstructorstandings.model.toEntity
import com.toquete.boxbox.data.fullconstructorstandings.source.local.FullConstructorStandingsLocalDataSource
import com.toquete.boxbox.data.fullconstructorstandings.source.remote.FullConstructorStandingsRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class DefaultFullConstructorStandingsRepository @Inject constructor(
    private val remoteDataSource: FullConstructorStandingsRemoteDataSource,
    private val localDataSource: FullConstructorStandingsLocalDataSource,
    private val constructorStandingsLocalDataSource: ConstructorStandingsLocalDataSource
): FullConstructorStandingsRepository {

    override fun getFullConstructorStandings(): Flow<List<FullConstructorStanding>> {
        return localDataSource.getFullConstructorStandings()
            .map { it.map(FullConstructorStandingEntity::toDomain) }
    }

    override suspend fun sync(): Boolean {
        return runCatching {
            remoteDataSource.getConstructorStandings()
                .also { list ->
                    constructorStandingsLocalDataSource.insertAll(
                        list.map(ConstructorStandingResponse::toEntity)
                    )
                }
        }.isSuccess
    }
}