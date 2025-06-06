package com.toquete.boxbox.data.races.repository

import com.toquete.boxbox.core.database.dao.CircuitDao
import com.toquete.boxbox.core.database.dao.RaceDao
import com.toquete.boxbox.core.database.model.RaceWithCircuitEntity
import com.toquete.boxbox.core.model.Race
import com.toquete.boxbox.data.races.model.toDomain
import com.toquete.boxbox.data.races.model.toEntity
import com.toquete.boxbox.data.races.source.remote.RaceRemoteDataSource
import com.toquete.boxbox.domain.repository.RaceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class DefaultRaceRepository @Inject constructor(
    private val remoteDataSource: RaceRemoteDataSource,
    private val raceDao: RaceDao,
    private val circuitDao: CircuitDao
) : RaceRepository {

    override fun getUpcomingRacesBySeason(season: String, today: String): Flow<List<Race>> {
        return raceDao.getUpcomingRacesBySeason(season, today)
            .map { it.map(RaceWithCircuitEntity::toDomain) }
    }

    override fun getPastRacesBySeason(season: String, today: String): Flow<List<Race>> {
        return raceDao.getPastRacesBySeason(season, today)
            .map { it.map(RaceWithCircuitEntity::toDomain) }
    }

    override suspend fun sync() {
        val list = remoteDataSource.getRaces()
        circuitDao.upsertAll(list.map { it.circuit.toEntity() })
        raceDao.upsertAll(list.map { it.toEntity() })
    }
}
