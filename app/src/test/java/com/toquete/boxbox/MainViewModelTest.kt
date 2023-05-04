package com.toquete.boxbox

import com.toquete.boxbox.core.testing.util.MainDispatcherRule
import com.toquete.boxbox.util.NetworkMonitor
import com.toquete.boxbox.util.SyncMonitor
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class MainViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val networkMonitor: NetworkMonitor = mockk(relaxed = true)
    private val syncMonitor: SyncMonitor = mockk(relaxed = true)

    private lateinit var viewModel: MainViewModel

    @Test
    fun `init should send isOnline true when app is online`() = runTest {
        val flow = MutableSharedFlow<Boolean>()
        coEvery { networkMonitor.isOnline } returns flow

        setupViewModel()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.isOnline.collect()
        }

        assertTrue(viewModel.isOnline.value)

        flow.emit(true)
        assertTrue(viewModel.isOnline.value)

        backgroundScope.cancel()
    }

    @Test
    fun `init should send isOnline false when app is not online`() = runTest {
        val flow = MutableSharedFlow<Boolean>()
        coEvery { networkMonitor.isOnline } returns flow

        setupViewModel()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.isOnline.collect()
        }

        assertTrue(viewModel.isOnline.value)

        flow.emit(false)
        assertFalse(viewModel.isOnline.value)

        backgroundScope.cancel()
    }

    @Test
    fun `init should send isSyncing true when app is syncing`() = runTest {
        val flow = MutableSharedFlow<Boolean>()
        coEvery { syncMonitor.isSyncing } returns flow

        setupViewModel()
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.isSyncing.collect()
        }

        assertTrue(viewModel.isSyncing.value)

        flow.emit(true)
        assertTrue(viewModel.isSyncing.value)

        backgroundScope.cancel()
    }

    @Test
    fun `init should send isSyncing false when app is not syncing`() = runTest {
        val flow = MutableSharedFlow<Boolean>()
        coEvery { syncMonitor.isSyncing } returns flow

        setupViewModel()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.isSyncing.collect()
        }

        assertTrue(viewModel.isSyncing.value)

        flow.emit(false)
        assertFalse(viewModel.isSyncing.value)

        backgroundScope.cancel()
    }

    private fun setupViewModel() {
        viewModel = MainViewModel(networkMonitor, syncMonitor)
    }
}
