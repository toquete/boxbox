package com.toquete.boxbox.data.constructorstandings

import com.toquete.boxbox.core.database.dao.ConstructorStandingDao
import com.toquete.boxbox.core.testing.data.constructorStandingEntities
import com.toquete.boxbox.data.constructorstandings.source.local.DefaultConstructorStandingsLocalDataSource
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

        dataSource.insertAll(constructorStandingEntities)

        coVerify {
            constructorStandingDao.deleteAndInsertInTransaction(constructorStandingEntities)
        }
    }
}