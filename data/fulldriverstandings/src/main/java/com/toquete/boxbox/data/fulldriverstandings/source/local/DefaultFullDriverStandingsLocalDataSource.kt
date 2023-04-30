package com.toquete.boxbox.data.fulldriverstandings.source.local

import com.toquete.boxbox.core.database.dao.FullDriverStandingDao
import com.toquete.boxbox.core.database.model.FullDriverStandingEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class DefaultFullDriverStandingsLocalDataSource @Inject constructor(
    private val fullDriverStandingDao: FullDriverStandingDao
) : FullDriverStandingsLocalDataSource {

    override fun getFullDriverStandings(): Flow<List<FullDriverStandingEntity>> {
        return fullDriverStandingDao.getFullDriverStandings()
    }
}