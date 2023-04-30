package com.toquete.boxbox.data.fullconstructorstandings.repository

import com.toquete.boxbox.core.database.model.ConstructorStandingEntity
import com.toquete.boxbox.core.testing.data.constructorStandingEntities
import com.toquete.boxbox.core.testing.data.constructorStandingsResponse
import com.toquete.boxbox.core.testing.data.fullConstructorStandingEntities
import com.toquete.boxbox.core.testing.data.fullConstructorStandings
import com.toquete.boxbox.data.constructorstandings.source.local.ConstructorStandingsLocalDataSource
import com.toquete.boxbox.data.fullconstructorstandings.source.local.FullConstructorStandingsLocalDataSource
import com.toquete.boxbox.data.fullconstructorstandings.source.remote.FullConstructorStandingsRemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException
import kotlin.test.assertContentEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DefaultFullConstructorStandingsRepositoryTest {

    private val remoteDataSource: FullConstructorStandingsRemoteDataSource = mockk(relaxed = true)
    private val localDataSource: FullConstructorStandingsLocalDataSource = mockk(relaxed = true)
    private val constructorStandingsLocalDataSource: ConstructorStandingsLocalDataSource = mockk(relaxed = true)

    private val repository = DefaultFullConstructorStandingsRepository(
        remoteDataSource,
        localDataSource,
        constructorStandingsLocalDataSource
    )

    @Test
    fun `getFullConstructorStandings should return mapped list when called`() = runTest {
        coEvery { localDataSource.getFullConstructorStandings() } returns flowOf(fullConstructorStandingEntities)

        val result = repository.getFullConstructorStandings()

        assertContentEquals(fullConstructorStandings, result.first())
    }

    @Test
    fun `sync should return true when remote data is saved in database`() = runTest {
        coEvery { remoteDataSource.getConstructorStandings() } returns constructorStandingsResponse
        coEvery { constructorStandingsLocalDataSource.insertAll(any()) } returns Unit

        val result = repository.sync()

        assertTrue(result)
    }

    @Test
    fun `sync should insert data in database when remote data is gotten successfully`() = runTest {
        val slot = slot<List<ConstructorStandingEntity>>()
        coEvery { remoteDataSource.getConstructorStandings() } returns constructorStandingsResponse
        coEvery { constructorStandingsLocalDataSource.insertAll(any()) } returns Unit

        repository.sync()

        coVerify { constructorStandingsLocalDataSource.insertAll(capture(slot)) }

        assertContentEquals(constructorStandingEntities, slot.captured)
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

        coVerify(exactly = 0) { constructorStandingsLocalDataSource.insertAll(any()) }
    }

    @Test
    fun `sync should return false when local data insertion returns error`() = runTest {
        coEvery { remoteDataSource.getConstructorStandings() } returns constructorStandingsResponse
        coEvery { constructorStandingsLocalDataSource.insertAll(any()) } throws IOException()

        val result = repository.sync()

        assertFalse(result)
    }
}