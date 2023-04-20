package com.toquete.boxbox.data.driverstandings.source.local

import com.toquete.boxbox.database.dao.DriverStandingDao
import com.toquete.boxbox.database.dao.DriverStandingQueryDao
import com.toquete.boxbox.database.model.asDomain
import com.toquete.boxbox.database.model.asEntity
import com.toquete.boxbox.model.DriverStanding
import javax.inject.Inject

internal class DriverStandingsLocalDataSourceImpl @Inject constructor(
    private val driverStandingDao: DriverStandingDao,
    private val driverStandingQueryDao: DriverStandingQueryDao
) : DriverStandingsLocalDataSource {

    override suspend fun getDriverStandings(): List<DriverStanding> {
        return driverStandingQueryDao.getDriverStandings()
            .map { it.asDomain() }
    }

    override suspend fun insertAll(driverStandings: List<DriverStanding>) {
        driverStandingDao.insertAll(driverStandings.map { it.asEntity() })
    }

    override suspend fun deleteAll() {
        driverStandingDao.deleteAll()
    }
}