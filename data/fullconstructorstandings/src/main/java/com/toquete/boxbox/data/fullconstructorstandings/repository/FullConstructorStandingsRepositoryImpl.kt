package com.toquete.boxbox.data.fullconstructorstandings.repository

import com.toquete.boxbox.data.fullconstructorstandings.model.toDomain
import com.toquete.boxbox.data.fullconstructorstandings.source.local.FullConstructorStandingsLocalDataSource
import com.toquete.boxbox.database.model.FullConstructorStandingEntity
import com.toquete.boxbox.model.FullConstructorStanding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class FullConstructorStandingsRepositoryImpl @Inject constructor(
    private val localDataSource: FullConstructorStandingsLocalDataSource
): FullConstructorStandingsRepository {

    override fun getFullConstructorStandings(): Flow<List<FullConstructorStanding>> {
        return localDataSource.getFullConstructorStandings()
            .map { it.map(FullConstructorStandingEntity::toDomain) }
    }
}