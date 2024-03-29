package com.toquete.boxbox.data.constructorstandings.source.local

import com.toquete.boxbox.core.database.dao.ConstructorStandingDao
import com.toquete.boxbox.core.testing.data.constructorStandingEntities
import com.toquete.boxbox.core.testing.data.fullConstructorStandingEntities
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertContentEquals

class DefaultConstructorStandingsLocalDataSourceTest {

    private val constructorStandingDao: ConstructorStandingDao = mockk(relaxed = true)
    private val dataSource = DefaultConstructorStandingsLocalDataSource(constructorStandingDao)

    @Test
    fun `insertAll should insert standings when called`() = runTest {
        coEvery { constructorStandingDao.deleteAndInsertAllInTransaction(any()) } returns Unit

        dataSource.insertAll(constructorStandingEntities)

        coVerify {
            constructorStandingDao.deleteAndInsertAllInTransaction(constructorStandingEntities)
        }
    }

    @Test
    fun `getConstructorStandings should return constructor standings when called`() = runTest {
        every {
            constructorStandingDao.getFullConstructorStandings()
        } returns flowOf(fullConstructorStandingEntities)

        val result = dataSource.getConstructorStandings().first()

        assertContentEquals(fullConstructorStandingEntities, result)
    }
}
