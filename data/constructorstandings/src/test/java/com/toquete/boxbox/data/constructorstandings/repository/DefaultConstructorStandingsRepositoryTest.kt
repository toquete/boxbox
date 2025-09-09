package com.toquete.boxbox.data.constructorstandings.repository

import com.toquete.boxbox.core.database.dao.ConstructorStandingDao
import com.toquete.boxbox.data.constructorstandings.mock.constructorStandingEntities
import com.toquete.boxbox.data.constructorstandings.mock.constructorStandings
import com.toquete.boxbox.data.constructorstandings.mock.constructorStandingsResponse
import com.toquete.boxbox.data.constructorstandings.mock.fullConstructorStandingEntities
import com.toquete.boxbox.data.constructorstandings.source.remote.ConstructorStandingsRemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException
import kotlin.test.assertContentEquals

class DefaultConstructorStandingsRepositoryTest {

    private val remoteDataSource: ConstructorStandingsRemoteDataSource = mockk(relaxed = true)
    private val constructorStandingDao: ConstructorStandingDao = mockk(relaxed = true)

    private val repository = DefaultConstructorStandingsRepository(remoteDataSource, constructorStandingDao)

    @Test
    fun `getFullConstructorStandings should return mapped list when called`() = runTest {
        every { constructorStandingDao.getFullConstructorStandings() } returns flowOf(fullConstructorStandingEntities)

        val result = repository.getConstructorStandings()

        assertContentEquals(constructorStandings, result.first())
    }

    @Test
    fun `sync should insert data in database when remote data is gotten successfully`() = runTest {
        coEvery { remoteDataSource.getConstructorStandings() } returns constructorStandingsResponse
        coEvery { constructorStandingDao.insertAll(any()) } returns Unit

        repository.sync()

        coVerify {
            remoteDataSource.getConstructorStandings()
            constructorStandingDao.deleteAndInsertAllInTransaction(constructorStandingEntities)
        }
    }

    @Test(expected = IOException::class)
    fun `sync should not call local data source when remote data returns error`() = runTest {
        coEvery { remoteDataSource.getConstructorStandings() } throws IOException()

        repository.sync()

        coVerify(exactly = 0) { constructorStandingDao.insertAll(any()) }
    }
}
