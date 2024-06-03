package com.toquete.boxbox.data.races.repository

import com.toquete.boxbox.core.database.model.RaceWithCircuitEntity
import com.toquete.boxbox.core.model.Race
import com.toquete.boxbox.data.circuits.source.local.CircuitLocalDataSource
import com.toquete.boxbox.data.races.model.toDomain
import com.toquete.boxbox.data.races.model.toEntity
import com.toquete.boxbox.data.races.source.local.RaceLocalDataSource
import com.toquete.boxbox.data.races.source.remote.RaceRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class DefaultRaceRepository @Inject constructor(
    private val remoteDataSource: RaceRemoteDataSource,
    private val localDataSource: RaceLocalDataSource,
    private val circuitLocalDataSource: CircuitLocalDataSource
) : RaceRepository {

    override fun getRacesBySeason(season: String): Flow<List<Race>> {
        return localDataSource.getRacesBySeason(season)
            .map { it.map(RaceWithCircuitEntity::toDomain) }
    }

    override fun getUpcomingRacesBySeason(season: String, today: String): Flow<List<Race>> {
        return localDataSource.getUpcomingRacesBySeason(season, today)
            .map { it.map(RaceWithCircuitEntity::toDomain) }
    }

    override fun getPastRacesBySeason(season: String, today: String): Flow<List<Race>> {
        return localDataSource.getPastRacesBySeason(season, today)
            .map { it.map(RaceWithCircuitEntity::toDomain) }
    }

    override suspend fun sync() {
        remoteDataSource.getRaces()
            .also { list ->
                circuitLocalDataSource.insertAll(list.map { it.circuit.toEntity() })
                localDataSource.insertAll(list.map { it.toEntity() })
            }
    }
}
