package com.toquete.boxbox.data.fulldriverstandings.repository

import com.toquete.boxbox.core.database.model.FullDriverStandingEntity
import com.toquete.boxbox.core.model.FullDriverStanding
import com.toquete.boxbox.data.constructors.source.local.ConstructorsLocalDataSource
import com.toquete.boxbox.data.drivers.source.local.DriversLocalDataSource
import com.toquete.boxbox.data.driverstandings.source.local.DriverStandingsLocalDataSource
import com.toquete.boxbox.data.fulldriverstandings.model.toDomain
import com.toquete.boxbox.data.fulldriverstandings.model.toEntity
import com.toquete.boxbox.data.fulldriverstandings.source.local.FullDriverStandingsLocalDataSource
import com.toquete.boxbox.data.fulldriverstandings.source.remote.FullDriverStandingsRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class DefaultFullDriverStandingsRepository @Inject constructor(
    private val remoteDataSource: FullDriverStandingsRemoteDataSource,
    private val localDataSource: FullDriverStandingsLocalDataSource,
    private val driverStandingsLocalDataSource: DriverStandingsLocalDataSource,
    private val driversLocalDataSource: DriversLocalDataSource,
    private val constructorsLocalDataSource: ConstructorsLocalDataSource
) : FullDriverStandingsRepository {

    override fun getFullDriverStandings(): Flow<List<FullDriverStanding>> {
        return localDataSource.getFullDriverStandings()
            .map { it.map(FullDriverStandingEntity::toDomain) }
    }

    override suspend fun sync(): Boolean {
        return runCatching {
            remoteDataSource.getFullDriverStandings()
                .also { list ->
                    driversLocalDataSource.insertAll(list.map { it.driver.toEntity() })
                    constructorsLocalDataSource.insertAll(list.map { it.constructors.first().toEntity() })
                    driverStandingsLocalDataSource.insertAll(list.map { it.toEntity() })
                }
        }.isSuccess
    }
}