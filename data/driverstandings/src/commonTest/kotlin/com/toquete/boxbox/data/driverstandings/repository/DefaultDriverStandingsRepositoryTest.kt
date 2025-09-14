package com.toquete.boxbox.data.driverstandings.repository

import com.toquete.boxbox.core.database.dao.ConstructorDao
import com.toquete.boxbox.core.database.dao.DriverDao
import com.toquete.boxbox.core.database.dao.DriverStandingDao
import com.toquete.boxbox.data.driverstandings.fake.FakeConstructorDao
import com.toquete.boxbox.data.driverstandings.fake.FakeDriverDao
import com.toquete.boxbox.data.driverstandings.fake.FakeDriverStandingDao
import com.toquete.boxbox.data.driverstandings.fake.FakeDriverStandingsRemoteDataSource
import com.toquete.boxbox.data.driverstandings.mock.constructorEntities
import com.toquete.boxbox.data.driverstandings.mock.driverEntities
import com.toquete.boxbox.data.driverstandings.mock.driverStandingEntities
import com.toquete.boxbox.data.driverstandings.mock.driverStandings
import com.toquete.boxbox.data.driverstandings.source.remote.DriverStandingsRemoteDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals

class DefaultDriverStandingsRepositoryTest {

    private lateinit var remoteDataSource: DriverStandingsRemoteDataSource
    private lateinit var driverStandingDao: DriverStandingDao
    private lateinit var driverDao: DriverDao
    private lateinit var constructorDao: ConstructorDao
    private lateinit var repository: DefaultDriverStandingsRepository

    @BeforeTest
    fun setUp() {
        remoteDataSource = FakeDriverStandingsRemoteDataSource()
        driverStandingDao = FakeDriverStandingDao()
        driverDao = FakeDriverDao()
        constructorDao = FakeConstructorDao()
        repository = DefaultDriverStandingsRepository(
            remoteDataSource,
            driverStandingDao,
            driverDao,
            constructorDao
        )
    }

    @Test
    fun `getDriverStandings should return mapped list when called`() = runTest {
        val result = repository.getDriverStandings()

        assertContentEquals(driverStandings, result.first())
    }

    @Test
    fun `sync should insert driver data in database when remote data is gotten successfully`() = runTest {
        repository.sync()

        val result = driverDao.getDrivers()

        assertContentEquals(driverEntities, result.first())
    }

    @Test
    fun `sync should insert constructor data in database when remote data is gotten successfully`() = runTest {
        repository.sync()

        val result = constructorDao.getConstructors()

        assertContentEquals(constructorEntities, result.first())
    }

    @Test
    fun `sync should insert driver standings data in database when remote data is gotten successfully`() = runTest {
        repository.sync()

        val result = driverStandingDao.getDriverStandings()

        assertContentEquals(driverStandingEntities, result.first())
    }
}
