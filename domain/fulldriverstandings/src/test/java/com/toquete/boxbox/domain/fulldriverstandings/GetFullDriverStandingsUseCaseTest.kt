package com.toquete.boxbox.domain.fulldriverstandings

import com.toquete.boxbox.core.testing.data.fullDriverStandings
import com.toquete.boxbox.data.fulldriverstandings.repository.FullDriverStandingsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertContentEquals

class GetFullDriverStandingsUseCaseTest {

    private val repository: FullDriverStandingsRepository = mockk()
    private val useCase = GetFullDriverStandingsUseCase(repository)

    @Test
    fun `invoke should return full driver standings when called`() = runTest {
        coEvery { repository.getFullDriverStandings() } returns flowOf(fullDriverStandings)

        val result = useCase()

        assertContentEquals(fullDriverStandings, result.first())
    }
}