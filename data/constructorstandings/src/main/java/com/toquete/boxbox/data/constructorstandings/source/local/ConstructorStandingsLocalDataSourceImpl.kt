package com.toquete.boxbox.data.constructorstandings.source.local

import com.toquete.boxbox.database.dao.ConstructorStandingDao
import com.toquete.boxbox.database.model.ConstructorStandingEntity
import javax.inject.Inject

class ConstructorStandingsLocalDataSourceImpl @Inject constructor(
    private val constructorStandingDao: ConstructorStandingDao
): ConstructorStandingsLocalDataSource {

    override suspend fun insertAll(constructorStandings: List<ConstructorStandingEntity>) {
        constructorStandingDao.insertAll(constructorStandings)
    }
}