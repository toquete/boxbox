package com.toquete.boxbox.domain.fullconstructorstandings

import com.toquete.boxbox.core.testing.data.fullConstructorStandings
import com.toquete.boxbox.data.fullconstructorstandings.repository.FullConstructorStandingsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertContentEquals

class GetFullConstructorStandingsUseCaseTest {

    private val repository: FullConstructorStandingsRepository = mockk()
    private val useCase = GetFullConstructorStandingsUseCase(repository)

    @Test
    fun `invoke should return full constructor standings when called`() = runTest {
        coEvery { repository.getFullConstructorStandings() } returns flowOf(fullConstructorStandings)

        val result = useCase()

        assertContentEquals(fullConstructorStandings, result.first())
    }
}