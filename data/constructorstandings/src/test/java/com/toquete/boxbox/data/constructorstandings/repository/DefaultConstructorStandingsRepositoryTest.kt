package com.toquete.boxbox.data.constructorstandings.repository

import com.toquete.boxbox.core.database.dao.ConstructorStandingDao
import com.toquete.boxbox.data.constructorstandings.fake.FakeConstructorStandingDao
import com.toquete.boxbox.data.constructorstandings.fake.FakeConstructorStandingsRemoteDataSource
import com.toquete.boxbox.data.constructorstandings.mock.constructorStandingEntities
import com.toquete.boxbox.data.constructorstandings.mock.constructorStandings
import com.toquete.boxbox.data.constructorstandings.source.remote.ConstructorStandingsRemoteDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertContentEquals

class DefaultConstructorStandingsRepositoryTest {

    private lateinit var remoteDataSource: ConstructorStandingsRemoteDataSource
    private lateinit var constructorStandingDao: ConstructorStandingDao
    private lateinit var repository: DefaultConstructorStandingsRepository

    @Before
    fun setUp() {
        remoteDataSource = FakeConstructorStandingsRemoteDataSource()
        constructorStandingDao = FakeConstructorStandingDao()
        repository = DefaultConstructorStandingsRepository(remoteDataSource, constructorStandingDao)
    }

    @Test
    fun `getFullConstructorStandings should return mapped list when called`() = runTest {
        val result = repository.getConstructorStandings()

        assertContentEquals(constructorStandings, result.first())
    }

    @Test
    fun `sync should insert data in database when remote data is gotten successfully`() = runTest {
        repository.sync()

        val result = constructorStandingDao.getConstructorStandings()

        assertContentEquals(constructorStandingEntities, result.first())
    }
}
