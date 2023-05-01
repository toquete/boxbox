package com.toquete.boxbox.data.driverstandings.source.local

import com.toquete.boxbox.core.database.dao.DriverStandingDao
import com.toquete.boxbox.core.database.model.DriverStandingEntity
import javax.inject.Inject

internal class DefaultDriverStandingsLocalDataSource @Inject constructor(
    private val driverStandingDao: DriverStandingDao
) : DriverStandingsLocalDataSource {

    override suspend fun insertAll(driverStandings: List<DriverStandingEntity>) {
        driverStandingDao.deleteAndInsertInTransaction(driverStandings)
    }
}