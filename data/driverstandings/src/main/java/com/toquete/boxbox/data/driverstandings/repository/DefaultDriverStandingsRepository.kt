package com.toquete.boxbox.data.driverstandings.repository

import com.toquete.boxbox.core.common.annotation.IoDispatcher
import com.toquete.boxbox.core.database.dao.DriverStandingDao
import com.toquete.boxbox.core.database.model.FullDriverStandingEntity
import com.toquete.boxbox.core.model.DriverStanding
import com.toquete.boxbox.data.constructors.source.local.ConstructorsLocalDataSource
import com.toquete.boxbox.data.drivers.source.local.DriversLocalDataSource
import com.toquete.boxbox.data.driverstandings.model.toDomain
import com.toquete.boxbox.data.driverstandings.model.toEntity
import com.toquete.boxbox.data.driverstandings.source.remote.DriverStandingsRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class DefaultDriverStandingsRepository @Inject constructor(
    private val remoteDataSource: DriverStandingsRemoteDataSource,
    private val driverStandingDao: DriverStandingDao,
    private val driversLocalDataSource: DriversLocalDataSource,
    private val constructorsLocalDataSource: ConstructorsLocalDataSource,
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
            driversLocalDataSource.insertAll(list.map { it.driver.toEntity() })
            constructorsLocalDataSource.insertAll(list.map { it.constructors.first().toEntity() })
            driverStandingDao.insertAll(list.map { it.toEntity() })
        }
    }
}
