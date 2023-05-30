package com.toquete.boxbox

import com.toquete.boxbox.core.model.DarkThemeConfig
import com.toquete.boxbox.core.preferences.model.UserPreferences
import com.toquete.boxbox.core.preferences.repository.UserPreferencesRepository
import com.toquete.boxbox.core.testing.data.userPreferences
import com.toquete.boxbox.core.testing.util.MainDispatcherRule
import com.toquete.boxbox.util.NetworkMonitor
import com.toquete.boxbox.util.SyncMonitor
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class MainViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val networkMonitor: NetworkMonitor = mockk(relaxed = true)
    private val syncMonitor: SyncMonitor = mockk(relaxed = true)
    private val preferencesRepository: UserPreferencesRepository = mockk(relaxed = true)

    private lateinit var viewModel: MainViewModel

    @Test
    fun `init should send success state`() = runTest {
        val isOnlineFlow = MutableSharedFlow<Boolean>()
        val isSyncingFlow = MutableSharedFlow<Boolean>()
        val hasFailedFlow = MutableSharedFlow<Boolean>()
        val userPreferencesFlow = MutableSharedFlow<UserPreferences>()
        every { networkMonitor.isOnline } returns isOnlineFlow
        every { syncMonitor.isSyncing } returns isSyncingFlow
        every { syncMonitor.hasFailed } returns hasFailedFlow
        every { preferencesRepository.userPreferences } returns userPreferencesFlow

        setupViewModel()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.state.collect()
        }

        assertEquals(MainState.Loading, viewModel.state.value)

        isOnlineFlow.emit(true)
        isSyncingFlow.emit(true)
        hasFailedFlow.emit(false)
        userPreferencesFlow.emit(userPreferences)
        assertEquals(
            MainState.Success(isOnline = true, isSyncing = true, hasFailed = false, DarkThemeConfig.FOLLOW_SYSTEM),
            viewModel.state.value
        )

        backgroundScope.cancel()
    }

    private fun setupViewModel() {
        viewModel = MainViewModel(networkMonitor, syncMonitor, preferencesRepository)
    }
}
