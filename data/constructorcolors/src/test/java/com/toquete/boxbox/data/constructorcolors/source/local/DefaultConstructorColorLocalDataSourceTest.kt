package com.toquete.boxbox.data.constructorcolors.source.local

import com.toquete.boxbox.core.database.dao.ConstructorColorDao
import com.toquete.boxbox.core.testing.data.constructorColorEntities
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DefaultConstructorColorLocalDataSourceTest {

    private val constructorColorDao: ConstructorColorDao = mockk(relaxed = true)
    private val dataSource = DefaultConstructorColorLocalDataSource(constructorColorDao)

    @Test
    fun `insertAll should insert all constructor colors when called`() = runTest {
        coEvery { constructorColorDao.upsertAll(any()) } returns Unit

        dataSource.insertAll(constructorColorEntities)

        coVerify { constructorColorDao.upsertAll(constructorColorEntities) }
    }
}
