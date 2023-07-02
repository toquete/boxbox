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
    fun `sync should insert data in database when remote data is gotten successfully`() = runTest {
        coEvery { remoteDataSource.getConstructorStandings() } returns constructorStandingsResponse
        coEvery { localDataSource.insertAll(any()) } returns Unit

        repository.sync()

        coVerify {
            remoteDataSource.getConstructorStandings()
            localDataSource.insertAll(constructorStandingEntities)
        }
    }

    @Test(expected = IOException::class)
    fun `sync should not call local data source when remote data returns error`() = runTest {
        coEvery { remoteDataSource.getConstructorStandings() } throws IOException()

        repository.sync()

        coVerify(exactly = 0) { localDataSource.insertAll(any()) }
    }
}
