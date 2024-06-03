package com.toquete.boxbox.feature.home.ui

import androidx.compose.material3.SnackbarDuration
import com.toquete.boxbox.core.common.util.NetworkMonitor
import com.toquete.boxbox.core.common.util.SyncMonitor
import com.toquete.boxbox.core.testing.util.MainDispatcherRule
import com.toquete.boxbox.core.ui.custom.SnackbarManager
import com.toquete.boxbox.feature.home.R
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkObject
import io.mockk.verify
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val syncMonitor: SyncMonitor = mockk()
    private val networkMonitor: NetworkMonitor = mockk()

    private lateinit var homeViewModel: HomeViewModel

    @Test
    fun `init should send sync and network states`() = runTest {
        val syncFlow = MutableSharedFlow<Boolean>()
        val hasFailedFlow = MutableSharedFlow<Boolean>()
        val networkFlow = MutableSharedFlow<Boolean>()
        coEvery { syncMonitor.isSyncing } returns syncFlow
        coEvery { syncMonitor.hasFailed } returns hasFailedFlow
        coEvery { networkMonitor.isOnline } returns networkFlow

        setupViewModel()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            homeViewModel.state.collect()
        }

        assertEquals(HomeState(), homeViewModel.state.value)

        syncFlow.emit(true)
        hasFailedFlow.emit(false)
        networkFlow.emit(true)

        assertEquals(HomeState(isSyncing = true, hasFailed = false, isOffline = false), homeViewModel.state.value)
        backgroundScope.cancel()
    }

    @Test
    fun `init should show snackbar when offline`() = runTest {
        mockkObject(SnackbarManager)
        val syncFlow = MutableSharedFlow<Boolean>()
        val hasFailedFlow = MutableSharedFlow<Boolean>()
        val networkFlow = MutableSharedFlow<Boolean>()
        coEvery { syncMonitor.isSyncing } returns syncFlow
        coEvery { syncMonitor.hasFailed } returns hasFailedFlow
        coEvery { networkMonitor.isOnline } returns networkFlow

        setupViewModel()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            homeViewModel.state.collect()
        }

        syncFlow.emit(false)
        hasFailedFlow.emit(false)
        networkFlow.emit(false)

        verify { SnackbarManager.showMessage(R.string.home_not_connected, duration = SnackbarDuration.Long) }

        backgroundScope.cancel()
        unmockkObject(SnackbarManager)
    }

    @Test
    fun `init should show snackbar when sync failed`() = runTest {
        mockkObject(SnackbarManager)
        val syncFlow = MutableSharedFlow<Boolean>()
        val hasFailedFlow = MutableSharedFlow<Boolean>()
        val networkFlow = MutableSharedFlow<Boolean>()
        coEvery { syncMonitor.isSyncing } returns syncFlow
        coEvery { syncMonitor.hasFailed } returns hasFailedFlow
        coEvery { networkMonitor.isOnline } returns networkFlow

        setupViewModel()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            homeViewModel.state.collect()
        }

        syncFlow.emit(true)
        hasFailedFlow.emit(true)
        networkFlow.emit(true)

        verify { SnackbarManager.showMessage(R.string.home_fail_message, duration = SnackbarDuration.Long) }

        backgroundScope.cancel()
        unmockkObject(SnackbarManager)
    }

    private fun setupViewModel() {
        homeViewModel = HomeViewModel(syncMonitor, networkMonitor)
    }
}
