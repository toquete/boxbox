package com.toquete.boxbox.feature.standings.drivers

import com.toquete.boxbox.core.model.FullDriverStanding
import com.toquete.boxbox.core.testing.data.fullDriverStandings
import com.toquete.boxbox.core.testing.util.MainDispatcherRule
import com.toquete.boxbox.domain.fulldriverstandings.GetFullDriverStandingsUseCase
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

    private val getFullDriverStandingsUseCase: GetFullDriverStandingsUseCase = mockk(relaxed = true)

    private lateinit var viewModel: FullDriverStandingsViewModel

    @Test
    fun `init should send success state when drivers standings are returned`() = runTest {
        val driversFlow = MutableSharedFlow<List<FullDriverStanding>>()
        coEvery { getFullDriverStandingsUseCase() } returns driversFlow

        setupViewModel()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.state.collect()
        }

        assertEquals(FullDriversStandingsState.Loading, viewModel.state.value)

        driversFlow.emit(fullDriverStandings)
        assertEquals(FullDriversStandingsState.Success(fullDriverStandings), viewModel.state.value)

        backgroundScope.cancel()
    }

    private fun setupViewModel() {
        viewModel = FullDriverStandingsViewModel(getFullDriverStandingsUseCase)
    }
}
