package com.toquete.boxbox.data.fulldriverstandings.source.local

import com.toquete.boxbox.core.database.dao.FullDriverStandingDao
import com.toquete.boxbox.core.testing.data.fullDriverStandingEntities
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertContentEquals

class DefaultFullDriverStandingLocalDataSourceTest {

    private val fullDriverStandingDao: FullDriverStandingDao = mockk()
    private val dataSource = DefaultFullDriverStandingsLocalDataSource(fullDriverStandingDao)

    @Test
    fun `getFullDriverStandings should emit full driver standings when called`() = runTest {
        coEvery { fullDriverStandingDao.getFullDriverStandings() } returns flowOf(fullDriverStandingEntities)

        val result = dataSource.getFullDriverStandings()

        assertContentEquals(fullDriverStandingEntities, result.first())
    }
}