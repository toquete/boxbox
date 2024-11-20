package com.toquete.boxbox.data.driverstandings.repository

import com.toquete.boxbox.core.common.annotation.IoDispatcher
import com.toquete.boxbox.core.database.dao.ConstructorDao
import com.toquete.boxbox.core.database.dao.DriverDao
import com.toquete.boxbox.core.database.dao.DriverStandingDao
import com.toquete.boxbox.core.database.model.FullDriverStandingEntity
import com.toquete.boxbox.core.model.DriverStanding
import com.toquete.boxbox.data.driverstandings.model.toDomain
import com.toquete.boxbox.data.driverstandings.model.toEntity
import com.toquete.boxbox.data.driverstandings.source.remote.DriverStandingsRemoteDataSource
import com.toquete.boxbox.domain.repository.DriverStandingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class DefaultDriverStandingsRepository @Inject constructor(
    private val remoteDataSource: DriverStandingsRemoteDataSource,
    private val driverStandingDao: DriverStandingDao,
    private val driverDao: DriverDao,
    private val constructorDao: ConstructorDao,
    @IoDispatcher private val dispatcher: CoroutineContext
) : DriverStandingsRepository {

    override fun getDriverStandings(): Flow<List<DriverStanding>> {
        return driverStandingDao.getFullDriverStandings()
            .map { it.map(FullDriverStandingEntity::toDomain) }
            .flowOn(dispatcher)
    }

    override suspend fun sync() {
        withContext(dispatcher) {
            val list = remoteDataSource.getDriverStandings()
            driverDao.upsertAll(list.map { it.driver.toEntity() })
            constructorDao.upsertAll(list.map { it.constructors.first().toEntity() })
            driverStandingDao.deleteAndInsertAllInTransaction(list.map { it.toEntity() })
        }
    }
}
