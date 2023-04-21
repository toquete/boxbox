package com.toquete.boxbox.data.fulldriverstandings.source.local

import com.toquete.boxbox.database.dao.FullDriverStandingDao
import com.toquete.boxbox.database.model.FullDriverStandingEntity
import javax.inject.Inject

internal class FullDriverStandingsLocalDataSourceImpl @Inject constructor(
    private val fullDriverStandingDao: FullDriverStandingDao
) : FullDriverStandingsLocalDataSource {

    override suspend fun getFullDriverStandings(): List<FullDriverStandingEntity> {
        return fullDriverStandingDao.getFullDriverStandings()
    }
}