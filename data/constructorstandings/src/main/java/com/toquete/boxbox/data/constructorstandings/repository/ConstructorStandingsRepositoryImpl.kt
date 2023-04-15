package com.toquete.boxbox.data.constructorstandings.repository

import com.toquete.boxbox.data.constructorstandings.source.remote.ConstructorStandingsRemoteDataSource
import com.toquete.boxbox.model.ConstructorStanding
import javax.inject.Inject

internal class ConstructorStandingsRepositoryImpl @Inject constructor(
    private val remoteDataSource: ConstructorStandingsRemoteDataSource
) : ConstructorStandingsRepository {

    override suspend fun getConstructorStandings(): List<ConstructorStanding> {
        return remoteDataSource.getConstructorStandings()
    }
}