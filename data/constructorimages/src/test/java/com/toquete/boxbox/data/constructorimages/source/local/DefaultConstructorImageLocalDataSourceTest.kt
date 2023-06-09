package com.toquete.boxbox.data.constructorimages.source.local

import com.toquete.boxbox.core.database.dao.ConstructorImageDao
import com.toquete.boxbox.core.testing.data.constructorImageEntities
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DefaultConstructorImageLocalDataSourceTest {

    private val constructorImageDao: ConstructorImageDao = mockk(relaxed = true)
    private val dataSource = DefaultConstructorImageLocalDataSource(constructorImageDao)

    @Test
    fun `insertAll should insert all constructor images when called`() = runTest {
        coEvery { constructorImageDao.upsertAll(any()) } returns Unit

        dataSource.insertAll(constructorImageEntities)

        coVerify { constructorImageDao.upsertAll(constructorImageEntities) }
    }
}
