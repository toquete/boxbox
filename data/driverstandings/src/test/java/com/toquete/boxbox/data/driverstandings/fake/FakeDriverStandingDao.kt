package com.toquete.boxbox.data.driverstandings.fake

import com.toquete.boxbox.core.database.dao.DriverStandingDao
import com.toquete.boxbox.core.database.model.DriverStandingEntity
import com.toquete.boxbox.core.database.model.FullDriverStandingEntity
import com.toquete.boxbox.data.driverstandings.mock.fullDriverStandingEntities
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class FakeDriverStandingDao : DriverStandingDao {

    private val entities: MutableStateFlow<List<DriverStandingEntity>> = MutableStateFlow(emptyList())

    override suspend fun insertAll(driverStandings: List<DriverStandingEntity>) {
        entities.update { oldValue ->
            oldValue + driverStandings
        }
    }

    override suspend fun deleteAll() {
        entities.update { emptyList() }
    }

    override fun getDriverStandings(): Flow<List<DriverStandingEntity>> {
        return entities.map { list ->
            list.sortedBy { it.position }
        }
    }

    override fun getFullDriverStandings(): Flow<List<FullDriverStandingEntity>> {
        return flowOf(fullDriverStandingEntities)
    }
}
