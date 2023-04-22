package com.toquete.boxbox.data.fullconstructorstandings.repository

import com.toquete.boxbox.data.constructorstandings.source.local.ConstructorStandingsLocalDataSource
import com.toquete.boxbox.data.fullconstructorstandings.model.toDomain
import com.toquete.boxbox.data.fullconstructorstandings.model.toEntity
import com.toquete.boxbox.data.fullconstructorstandings.source.local.FullConstructorStandingsLocalDataSource
import com.toquete.boxbox.data.fullconstructorstandings.source.remote.FullConstructorStandingsRemoteDataSource
import com.toquete.boxbox.database.model.FullConstructorStandingEntity
import com.toquete.boxbox.model.FullConstructorStanding
import com.toquete.boxbox.network.model.ConstructorStandingResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class FullConstructorStandingsRepositoryImpl @Inject constructor(
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