package com.toquete.boxbox.data.driverstandings.source.local

import com.toquete.boxbox.core.database.dao.DriverStandingDao
import com.toquete.boxbox.core.database.model.DriverStandingEntity
import com.toquete.boxbox.core.database.model.NewFullDriverStandingEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class DefaultDriverStandingsLocalDataSource @Inject constructor(
    private val driverStandingDao: DriverStandingDao
) : DriverStandingsLocalDataSource {

    override suspend fun insertAll(driverStandings: List<DriverStandingEntity>) {
        driverStandingDao.deleteAndInsertInTransaction(driverStandings)
    }

    override fun getDriverStandings(): Flow<List<NewFullDriverStandingEntity>> {
        return driverStandingDao.getFullDriverStandings()
    }
}
