package com.toquete.boxbox.data.constructorstandings

import com.toquete.boxbox.data.constructorstandings.source.local.DefaultConstructorStandingsLocalDataSource
import com.toquete.boxbox.database.dao.ConstructorStandingDao
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DefaultConstructorStandingsLocalDataSourceTest {

    private val constructorStandingDao: ConstructorStandingDao = mockk(relaxed = true)
    private val dataSource = DefaultConstructorStandingsLocalDataSource(constructorStandingDao)

    @Test
    fun `insertAll should insert standings when called`() = runTest {
        coEvery { constructorStandingDao.insertAll(any()) } returns Unit

        dataSource.insertAll(FakeLocalData.constructorStandings)

        coVerify {
            constructorStandingDao.deleteAndInsertInTransaction(FakeLocalData.constructorStandings)
        }
    }
}