package com.toquete.boxbox.data.constructorstandings.source.local

import com.toquete.boxbox.core.database.dao.ConstructorStandingDao
import com.toquete.boxbox.core.database.model.ConstructorStandingEntity
import com.toquete.boxbox.core.database.model.FullConstructorStandingEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultConstructorStandingsLocalDataSource @Inject constructor(
    private val constructorStandingDao: ConstructorStandingDao
) : ConstructorStandingsLocalDataSource {

    override suspend fun insertAll(constructorStandings: List<ConstructorStandingEntity>) {
        constructorStandingDao.upsertAll(constructorStandings)
    }

    override fun getConstructorStandings(): Flow<List<FullConstructorStandingEntity>> {
        return constructorStandingDao.getFullConstructorStandings()
    }
}
