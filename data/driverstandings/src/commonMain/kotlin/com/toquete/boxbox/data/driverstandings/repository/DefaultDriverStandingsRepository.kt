package com.toquete.boxbox.data.driverstandings.repository

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
import kotlinx.coroutines.flow.map

internal class DefaultDriverStandingsRepository(
    private val remoteDataSource: DriverStandingsRemoteDataSource,
    private val driverStandingDao: DriverStandingDao,
    private val driverDao: DriverDao,
    private val constructorDao: ConstructorDao
) : DriverStandingsRepository {

    override fun getDriverStandings(): Flow<List<DriverStanding>> {
        return driverStandingDao.getFullDriverStandings()
            .map { it.map(FullDriverStandingEntity::toDomain) }
    }

    override suspend fun sync() {
        val list = remoteDataSource.getDriverStandings()
        driverDao.upsertAll(list.map { it.driver.toEntity() })
        constructorDao.upsertAll(list.map { it.constructors.first().toEntity() })
        driverStandingDao.deleteAndInsertAllInTransaction(
            list.mapIndexed { index, driverStanding ->
                driverStanding.toEntity(index)
            }
        )
    }
}
