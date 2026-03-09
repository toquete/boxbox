package com.toquete.boxbox.domain.usecase

import com.toquete.boxbox.core.model.ColorConfig
import com.toquete.boxbox.core.model.DarkThemeConfig
import com.toquete.boxbox.core.model.UserPreferences
import com.toquete.boxbox.domain.repository.UserPreferencesRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class IsSyncAllowedUseCaseTest {

    private val getTodayLocalDateUseCase: GetTodayLocalDateUseCase = mockk()
    private val userPreferencesRepository: UserPreferencesRepository = mockk()
    private val useCase = IsSyncAllowedUseCase(getTodayLocalDateUseCase, userPreferencesRepository)

    @Test
    fun `invoke returns true on Sunday when last updated date is not null`() = runTest {
        every { getTodayLocalDateUseCase() } returns LocalDate(year = 2024, month = 12, day = 1) // Sunday
        coEvery { userPreferencesRepository.userPreferences } returns
            flowOf(userPreferences(lastUpdatedDateInMillis = 1000))

        assertTrue(useCase())
    }

    @Test
    fun `invoke returns true on Monday when last updated date is not null`() = runTest {
        every { getTodayLocalDateUseCase() } returns LocalDate(year = 2024, month = 12, day = 2) // Monday
        coEvery { userPreferencesRepository.userPreferences } returns
            flowOf(userPreferences(lastUpdatedDateInMillis = 1000))

        assertTrue(useCase())
    }

    @Test
    fun `invoke returns false on a non-allowed day when last updated date is not null`() = runTest {
        every { getTodayLocalDateUseCase() } returns LocalDate(year = 2024, month = 12, day = 3) // Tuesday
        coEvery { userPreferencesRepository.userPreferences } returns
            flowOf(userPreferences(lastUpdatedDateInMillis = 1000))

        assertFalse(useCase())
    }

    @Test
    fun `invoke returns true on a non-allowed day when last updated date is null`() = runTest {
        every { getTodayLocalDateUseCase() } returns LocalDate(year = 2024, month = 12, day = 3) // Tuesday
        coEvery { userPreferencesRepository.userPreferences } returns
            flowOf(userPreferences(lastUpdatedDateInMillis = null))

        assertTrue(useCase())
    }

    private fun userPreferences(lastUpdatedDateInMillis: Long?) = UserPreferences(
        darkThemeConfig = DarkThemeConfig.DARK,
        colorConfig = ColorConfig.DEFAULT,
        lastUpdatedDateInMillis = lastUpdatedDateInMillis
    )
}
