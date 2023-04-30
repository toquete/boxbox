package com.toquete.boxbox.data.fullconstructorstandings.source.local

import com.toquete.boxbox.core.testing.data.fullConstructorStandingEntities
import com.toquete.boxbox.database.dao.FullConstructorStandingDao
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertContentEquals

class DefaultFullConstructorStandingsLocalDataSourceTest {

    private val fullConstructorStandingDao: FullConstructorStandingDao = mockk()
    private val dataSource = DefaultFullConstructorStandingsLocalDataSource(fullConstructorStandingDao)

    @Test
    fun `getFullConstructorStandings should emit full constructor standings when called`() = runTest {
        coEvery {
            fullConstructorStandingDao.getFullConstructorStandings()
        } returns flowOf(fullConstructorStandingEntities)

        val result = dataSource.getFullConstructorStandings()

        assertContentEquals(fullConstructorStandingEntities, result.first())
    }
}