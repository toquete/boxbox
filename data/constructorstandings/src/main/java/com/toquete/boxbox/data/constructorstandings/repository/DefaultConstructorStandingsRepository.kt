package com.toquete.boxbox.data.constructorstandings.repository

import com.toquete.boxbox.core.database.dao.ConstructorStandingDao
import com.toquete.boxbox.core.database.model.FullConstructorStandingEntity
import com.toquete.boxbox.core.model.ConstructorStanding
import com.toquete.boxbox.core.network.model.ConstructorStandingResponse
import com.toquete.boxbox.data.constructorstandings.model.toDomain
import com.toquete.boxbox.data.constructorstandings.model.toEntity
import com.toquete.boxbox.data.constructorstandings.source.remote.ConstructorStandingsRemoteDataSource
import com.toquete.boxbox.domain.repository.ConstructorStandingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class DefaultConstructorStandingsRepository @Inject constructor(
    private val remoteDataSource: ConstructorStandingsRemoteDataSource,
    private val constructorStandingDao: ConstructorStandingDao
) : ConstructorStandingsRepository {

    override fun getConstructorStandings(): Flow<List<ConstructorStanding>> {
        return constructorStandingDao.getFullConstructorStandings()
            .map { it.map(FullConstructorStandingEntity::toDomain) }
    }

    override suspend fun sync() {
        val list = remoteDataSource.getConstructorStandings()
        constructorStandingDao.deleteAndInsertAllInTransaction(list.map(ConstructorStandingResponse::toEntity))
    }
}
