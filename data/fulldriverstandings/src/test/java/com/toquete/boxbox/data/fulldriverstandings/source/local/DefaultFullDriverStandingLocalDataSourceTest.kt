package com.toquete.boxbox.data.fulldriverstandings.source.local

import com.toquete.boxbox.core.testing.data.fullDriverStandingEntities
import com.toquete.boxbox.database.dao.FullDriverStandingDao
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class DefaultFullDriverStandingLocalDataSourceTest {

    private val fullDriverStandingDao: FullDriverStandingDao = mockk()
    private val dataSource = DefaultFullDriverStandingsLocalDataSource(fullDriverStandingDao)

    @Test
    fun `getFullDriverStandings should emit full driver standings when called`() = runTest {
        coEvery { fullDriverStandingDao.getFullDriverStandings() } returns flowOf(fullDriverStandingEntities)

        val result = dataSource.getFullDriverStandings()

        assertEquals(fullDriverStandingEntities, result.first())
    }
}