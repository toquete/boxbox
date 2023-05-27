package com.toquete.boxbox.data.driverstandings.repository

import com.toquete.boxbox.core.database.model.FullDriverStandingEntity
import com.toquete.boxbox.core.model.FullDriverStanding
import com.toquete.boxbox.data.constructors.source.local.ConstructorsLocalDataSource
import com.toquete.boxbox.data.drivers.source.local.DriversLocalDataSource
import com.toquete.boxbox.data.driverstandings.model.toDomain
import com.toquete.boxbox.data.driverstandings.model.toEntity
import com.toquete.boxbox.data.driverstandings.source.local.DriverStandingsLocalDataSource
import com.toquete.boxbox.data.driverstandings.source.remote.DriverStandingsRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class DefaultDriverStandingsRepository @Inject constructor(
    private val remoteDataSource: DriverStandingsRemoteDataSource,
    private val localDataSource: DriverStandingsLocalDataSource,
    private val driversLocalDataSource: DriversLocalDataSource,
    private val constructorsLocalDataSource: ConstructorsLocalDataSource
) : DriverStandingsRepository {

    override fun getDriverStandings(): Flow<List<FullDriverStanding>> {
        return localDataSource.getDriverStandings()
            .map { it.map(FullDriverStandingEntity::toDomain) }
    }

    override suspend fun sync(): Boolean {
        return runCatching {
            remoteDataSource.getDriverStandings()
                .also { list ->
                    driversLocalDataSource.insertAll(list.map { it.driver.toEntity() })
                    constructorsLocalDataSource.insertAll(list.map { it.constructors.first().toEntity() })
                    localDataSource.insertAll(list.map { it.toEntity() })
                }
        }.isSuccess
    }
}
