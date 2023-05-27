package com.toquete.boxbox.feature.standings.drivers

import com.toquete.boxbox.core.model.DriverStanding
import com.toquete.boxbox.core.testing.data.driverStandings
import com.toquete.boxbox.core.testing.util.MainDispatcherRule
import com.toquete.boxbox.data.driverstandings.repository.DriverStandingsRepository
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
import kotlin.test.assertEquals

class FullDriverStandingsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repository: DriverStandingsRepository = mockk(relaxed = true)

    private lateinit var viewModel: FullDriverStandingsViewModel

    @Test
    fun `init should send success state when drivers standings are returned`() = runTest {
        val driversFlow = MutableSharedFlow<List<DriverStanding>>()
        coEvery { repository.getDriverStandings() } returns driversFlow

        setupViewModel()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.state.collect()
        }

        assertEquals(FullDriversStandingsState.Loading, viewModel.state.value)

        driversFlow.emit(driverStandings)
        assertEquals(FullDriversStandingsState.Success(driverStandings), viewModel.state.value)

        backgroundScope.cancel()
    }

    private fun setupViewModel() {
        viewModel = FullDriverStandingsViewModel(repository)
    }
}
