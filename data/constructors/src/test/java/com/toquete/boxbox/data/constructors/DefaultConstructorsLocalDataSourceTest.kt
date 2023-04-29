package com.toquete.boxbox.data.constructors

import com.toquete.boxbox.core.testing.data.constructorEntities
import com.toquete.boxbox.data.constructors.source.local.DefaultConstructorsLocalDataSource
import com.toquete.boxbox.database.dao.ConstructorDao
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DefaultConstructorsLocalDataSourceTest {

    private val constructorDao: ConstructorDao = mockk()
    private val dataSource = DefaultConstructorsLocalDataSource(constructorDao)

    @Test
    fun `insertAll should insert constructors when called`() = runTest {
        coEvery { dataSource.insertAll(any()) } returns Unit

        dataSource.insertAll(constructorEntities)

        coVerify { constructorDao.insertAll(constructorEntities) }
    }
}