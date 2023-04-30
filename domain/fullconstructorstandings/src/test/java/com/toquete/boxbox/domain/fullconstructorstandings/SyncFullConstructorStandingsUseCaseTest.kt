package com.toquete.boxbox.domain.fullconstructorstandings

import com.toquete.boxbox.data.fullconstructorstandings.repository.FullConstructorStandingsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SyncFullConstructorStandingsUseCaseTest {

    private val repository: FullConstructorStandingsRepository = mockk()
    private val useCase = SyncFullConstructorStandingsUseCase(repository)

    @Test
    fun `invoke should return true when data is synced successfully`() = runTest {
        coEvery { repository.sync() } returns true

        val result = useCase()

        assertTrue(result)
    }

    @Test
    fun `invoke should return false when data is not synced successfully`() = runTest {
        coEvery { repository.sync() } returns false

        val result = useCase()

        assertFalse(result)
    }
}