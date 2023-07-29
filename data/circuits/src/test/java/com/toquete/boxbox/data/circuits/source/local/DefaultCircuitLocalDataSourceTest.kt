package com.toquete.boxbox.data.circuits.source.local

import com.toquete.boxbox.core.database.dao.CircuitDao
import com.toquete.boxbox.core.testing.data.circuitEntities
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DefaultCircuitLocalDataSourceTest {

    private val dao: CircuitDao = mockk(relaxed = true)
    private val dataSource = DefaultCircuitLocalDataSource(dao)

    @Test
    fun `insertAll should call dao upsertAll`() = runTest {
        coEvery { dao.upsertAll(any()) } returns Unit

        dataSource.insertAll(circuitEntities)

        coVerify { dao.upsertAll(circuitEntities) }
    }
}
