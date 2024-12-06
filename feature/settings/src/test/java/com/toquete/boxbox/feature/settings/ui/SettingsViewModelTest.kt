package com.toquete.boxbox.feature.settings.ui

import com.toquete.boxbox.core.model.ColorConfig
import com.toquete.boxbox.core.model.DarkThemeConfig
import com.toquete.boxbox.core.model.UserPreferences
import com.toquete.boxbox.core.testing.data.preferences
import com.toquete.boxbox.core.testing.util.MainDispatcherRule
import com.toquete.boxbox.domain.repository.UserPreferencesRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.TimeZone
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class SettingsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val userPreferencesRepository: UserPreferencesRepository = mockk(relaxed = true)
    private val timeZone = TimeZone.of("America/Sao_Paulo")

    private lateinit var viewModel: SettingsViewModel

    @Test
    fun `init should send user preferences when data is returned`() = runTest {
        val userPreferencesFlow = MutableSharedFlow<UserPreferences>()
        coEvery { userPreferencesRepository.userPreferences } returns userPreferencesFlow

        setupViewModel()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.state.collect()
        }

        assertEquals(SettingsState(), viewModel.state.value)

        userPreferencesFlow.emit(preferences)
        assertEquals(
            SettingsState(
                isLoading = false,
                darkThemeConfig = preferences.darkThemeConfig,
                colorConfig = preferences.colorConfig,
                lastUpdatedTime = "2024-01-01 12:00:00"
            ),
            viewModel.state.value
        )

        backgroundScope.cancel()
    }

    @Test
    fun `init should send null time when time is null`() = runTest {
        val userPreferencesFlow = MutableSharedFlow<UserPreferences>()
        coEvery { userPreferencesRepository.userPreferences } returns userPreferencesFlow

        setupViewModel()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.state.collect()
        }

        assertEquals(SettingsState(), viewModel.state.value)

        userPreferencesFlow.emit(preferences.copy(lastUpdatedDateInMillis = null))
        assertEquals(
            SettingsState(
                isLoading = false,
                darkThemeConfig = preferences.darkThemeConfig,
                colorConfig = preferences.colorConfig,
                lastUpdatedTime = null
            ),
            viewModel.state.value
        )

        backgroundScope.cancel()
    }

    @Test
    fun `onThemeSettingsItemClick should set selected dark theme config`() = runTest {
        val config = DarkThemeConfig.DARK
        setupViewModel()

        viewModel.onThemeSettingsItemClick(config)

        coVerify { userPreferencesRepository.setDarkThemeConfig(config) }
    }

    @Test
    fun `onColorSettingsItemClick should set selected color config`() = runTest {
        val config = ColorConfig.DEFAULT
        setupViewModel()

        viewModel.onColorSettingsItemClick(config)

        coVerify { userPreferencesRepository.setColorConfig(config) }
    }

    private fun setupViewModel() {
        viewModel = SettingsViewModel(userPreferencesRepository, timeZone)
    }
}
