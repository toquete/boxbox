package com.toquete.boxbox.data.fullconstructorstandings.source.local

import com.toquete.boxbox.core.database.dao.FullConstructorStandingDao
import com.toquete.boxbox.core.database.model.FullConstructorStandingEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultFullConstructorStandingsLocalDataSource @Inject constructor(
    private val fullConstructorStandingDao: FullConstructorStandingDao
) : FullConstructorStandingsLocalDataSource {

    override fun getFullConstructorStandings(): Flow<List<FullConstructorStandingEntity>> {
        return fullConstructorStandingDao.getFullConstructorStandings()
    }
}
