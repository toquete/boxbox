package com.toquete.boxbox.data.constructorstandings.repository

import com.toquete.boxbox.core.testing.data.constructorStandingEntities
import com.toquete.boxbox.core.testing.data.constructorStandings
import com.toquete.boxbox.core.testing.data.constructorStandingsResponse
import com.toquete.boxbox.core.testing.data.fullConstructorStandingEntities
import com.toquete.boxbox.data.constructorstandings.source.local.ConstructorStandingsLocalDataSource
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
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DefaultConstructorStandingsRepositoryTest {

    private val remoteDataSource: ConstructorStandingsRemoteDataSource = mockk(relaxed = true)
    private val localDataSource: ConstructorStandingsLocalDataSource = mockk(relaxed = true)

    private val repository = DefaultConstructorStandingsRepository(
        remoteDataSource,
        localDataSource
    )

    @Test
    fun `getFullConstructorStandings should return mapped list when called`() = runTest {
        every { localDataSource.getConstructorStandings() } returns flowOf(fullConstructorStandingEntities)

        val result = repository.getConstructorStandings()

        assertContentEquals(constructorStandings, result.first())
    }

    @Test
    fun `sync should return true when remote data is saved in database`() = runTest {
        coEvery { remoteDataSource.getConstructorStandings() } returns constructorStandingsResponse
        coEvery { localDataSource.insertAll(any()) } returns Unit

        val result = repository.sync()

        assertTrue(result)
    }

    @Test
    fun `sync should insert data in database when remote data is gotten successfully`() = runTest {
        coEvery { remoteDataSource.getConstructorStandings() } returns constructorStandingsResponse
        coEvery { localDataSource.insertAll(any()) } returns Unit

        repository.sync()

        coVerify { localDataSource.insertAll(constructorStandingEntities) }
    }

    @Test
    fun `sync should return false when remote data returns error`() = runTest {
        coEvery { remoteDataSource.getConstructorStandings() } throws IOException()

        val result = repository.sync()

        assertFalse(result)
    }

    @Test
    fun `sync should not call local data source when remote data returns error`() = runTest {
        coEvery { remoteDataSource.getConstructorStandings() } throws IOException()

        repository.sync()

        coVerify(exactly = 0) { localDataSource.insertAll(any()) }
    }

    @Test
    fun `sync should return false when local data insertion returns error`() = runTest {
        coEvery { remoteDataSource.getConstructorStandings() } returns constructorStandingsResponse
        coEvery { localDataSource.insertAll(any()) } throws IOException()

        val result = repository.sync()

        assertFalse(result)
    }
}
