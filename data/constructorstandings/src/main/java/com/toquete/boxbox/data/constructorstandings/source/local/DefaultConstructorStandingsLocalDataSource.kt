package com.toquete.boxbox.data.constructorstandings.source.local

import com.toquete.boxbox.core.database.dao.ConstructorStandingDao
import com.toquete.boxbox.core.database.model.ConstructorStandingEntity
import com.toquete.boxbox.core.database.model.NewFullConstructorStandingEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultConstructorStandingsLocalDataSource @Inject constructor(
    private val constructorStandingDao: ConstructorStandingDao
) : ConstructorStandingsLocalDataSource {

    override suspend fun insertAll(constructorStandings: List<ConstructorStandingEntity>) {
        constructorStandingDao.deleteAndInsertInTransaction(constructorStandings)
    }

    override fun getConstructorStandings(): Flow<List<NewFullConstructorStandingEntity>> {
        return constructorStandingDao.getFullConstructorStandings()
    }
}
