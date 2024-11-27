package com.toquete.boxbox.feature.home.ui

import androidx.compose.material3.SnackbarDuration
import com.toquete.boxbox.core.common.util.NetworkMonitor
import com.toquete.boxbox.core.common.util.SyncMonitor
import com.toquete.boxbox.core.testing.util.MainDispatcherRule
import com.toquete.boxbox.core.ui.custom.SnackbarManager
import com.toquete.boxbox.domain.repository.SyncRepository
import com.toquete.boxbox.feature.home.R
import io.mockk.coEvery
import io.mockk.coVerify
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
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val syncMonitor: SyncMonitor = mockk(relaxed = true)
    private val networkMonitor: NetworkMonitor = mockk(relaxed = true)
    private val syncRepository: SyncRepository = mockk(relaxed = true)

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup() {
        mockkObject(SnackbarManager)
    }

    @After
    fun tearDown() {
        unmockkObject(SnackbarManager)
    }

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

        assertEquals(
            HomeState(
                isSyncing = true,
                hasFailed = false,
                isOffline = false,
                isRefreshing = false
            ),
            homeViewModel.state.value
        )
        backgroundScope.cancel()
    }

    @Test
    fun `init should show snackbar when offline`() = runTest {
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
    }

    @Test
    fun `init should show snackbar when sync failed`() = runTest {
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
    }

    @Test
    fun `refresh should show snackbar when sync failed`() = runTest {
        coEvery { syncRepository.sync() } throws Exception()

        setupViewModel()
        homeViewModel.refresh()

        verify {
            SnackbarManager.showMessage(
                R.string.home_fail_refresh,
                duration = SnackbarDuration.Long,
                withDismissAction = true
            )
        }
    }

    @Test
    fun `refresh should call sync`() = runTest {
        coEvery { syncRepository.sync() } returns Unit

        setupViewModel()
        homeViewModel.refresh()

        coVerify { syncRepository.sync() }
        assertEquals(
            HomeState(isRefreshing = false),
            homeViewModel.state.value
        )
    }

    private fun setupViewModel() {
        homeViewModel = HomeViewModel(syncMonitor, networkMonitor, syncRepository)
    }
}
