package com.toquete.boxbox.data.fulldriverstandings.source.local

import com.toquete.boxbox.database.dao.FullDriverStandingDao
import com.toquete.boxbox.database.model.asFullDomain
import com.toquete.boxbox.model.FullDriverStanding
import javax.inject.Inject

internal class FullDriverStandingsLocalDataSourceImpl @Inject constructor(
    private val fullDriverStandingDao: FullDriverStandingDao
) : FullDriverStandingsLocalDataSource {

    override suspend fun getFullDriverStandings(): List<FullDriverStanding> {
        return fullDriverStandingDao.getFullDriverStandings()
            .map { it.asFullDomain() }
    }
}