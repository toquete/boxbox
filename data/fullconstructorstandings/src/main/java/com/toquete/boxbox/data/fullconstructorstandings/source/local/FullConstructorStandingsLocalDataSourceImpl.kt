package com.toquete.boxbox.data.fullconstructorstandings.source.local

import com.toquete.boxbox.database.dao.FullConstructorStandingDao
import com.toquete.boxbox.database.model.FullConstructorStandingEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FullConstructorStandingsLocalDataSourceImpl @Inject constructor(
    private val fullConstructorStandingDao: FullConstructorStandingDao
): FullConstructorStandingsLocalDataSource {

    override fun getFullConstructorStandings(): Flow<List<FullConstructorStandingEntity>> {
        return fullConstructorStandingDao.getFullConstructorStandings()
    }
}