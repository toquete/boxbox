package com.toquete.boxbox.data.constructorstandings.source.local

import com.toquete.boxbox.core.database.dao.ConstructorStandingDao
import com.toquete.boxbox.core.database.model.ConstructorStandingEntity
import javax.inject.Inject

class DefaultConstructorStandingsLocalDataSource @Inject constructor(
    private val constructorStandingDao: ConstructorStandingDao
): ConstructorStandingsLocalDataSource {

    override suspend fun insertAll(constructorStandings: List<ConstructorStandingEntity>) {
        constructorStandingDao.deleteAndInsertInTransaction(constructorStandings)
    }
}