package com.toquete.boxbox.ui

import com.toquete.boxbox.core.model.ColorConfig
import com.toquete.boxbox.core.model.DarkThemeConfig
import com.toquete.boxbox.core.model.UserPreferences
import com.toquete.boxbox.core.testing.data.preferences
import com.toquete.boxbox.core.testing.util.MainDispatcherRule
import com.toquete.boxbox.domain.repository.RemoteConfigRepository
import com.toquete.boxbox.domain.repository.UserPreferencesRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import java.io.IOException
import kotlin.test.assertEquals

class MainViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val preferencesRepository: UserPreferencesRepository = mockk(relaxed = true)
    private val remoteConfigRepository: RemoteConfigRepository = mockk(relaxed = true)

    private lateinit var viewModel: MainViewModel

    @Test
    fun `init should send success state`() = runTest {
        val userPreferencesFlow = MutableSharedFlow<UserPreferences>()
        val remoteConfigFlow = MutableSharedFlow<Boolean>()
        every { preferencesRepository.userPreferences } returns userPreferencesFlow
        every { remoteConfigRepository.fetchAndActivate() } returns remoteConfigFlow

        setupViewModel()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.state.collect()
        }

        assertEquals(MainState(), viewModel.state.value)

        userPreferencesFlow.emit(preferences)
        remoteConfigFlow.emit(true)

        assertEquals(
            MainState(
                isLoading = false,
                darkThemeConfig = DarkThemeConfig.FOLLOW_SYSTEM,
                colorConfig = ColorConfig.DEFAULT
            ),
            viewModel.state.value
        )

        backgroundScope.cancel()
    }

    @Test
    fun `init should send default state with loading false on error`() = runTest {
        val userPreferencesFlow = MutableSharedFlow<UserPreferences>()
        every { preferencesRepository.userPreferences } returns userPreferencesFlow
        every { remoteConfigRepository.fetchAndActivate() } returns flow { throw IOException() }

        setupViewModel()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.state.collect()
        }

        userPreferencesFlow.emit(preferences)

        assertEquals(MainState(isLoading = false), viewModel.state.value)

        backgroundScope.cancel()
    }

    private fun setupViewModel() {
        viewModel = MainViewModel(preferencesRepository, remoteConfigRepository)
    }
}
