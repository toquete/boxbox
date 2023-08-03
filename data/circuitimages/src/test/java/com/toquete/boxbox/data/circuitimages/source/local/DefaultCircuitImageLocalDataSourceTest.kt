package com.toquete.boxbox.data.circuitimages.source.local

import com.toquete.boxbox.core.database.dao.CircuitImageDao
import com.toquete.boxbox.core.testing.data.circuitImageEntities
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DefaultCircuitImageLocalDataSourceTest {

    private val dao: CircuitImageDao = mockk(relaxed = true)
    private val dataSource = DefaultCircuitImageLocalDataSource(dao)

    @Test
    fun `insertAll should call dao upsertAll`() = runTest {
        coEvery { dao.upsertAll(any()) } returns Unit

        dataSource.insertAll(circuitImageEntities)

        coVerify { dao.upsertAll(circuitImageEntities) }
    }
}
