package com.toquete.boxbox.feature.races.ui

import com.toquete.boxbox.core.model.Race
import com.toquete.boxbox.core.testing.data.races
import com.toquete.boxbox.core.testing.util.MainDispatcherRule
import com.toquete.boxbox.domain.races.usecase.GetCurrentSeasonRacesUseCase
import com.toquete.boxbox.domain.races.usecase.GetPastRacesInCurrentSeasonUseCase
import com.toquete.boxbox.domain.races.usecase.GetUpcomingRacesInCurrentSeasonUseCase
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

class RacesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getCurrentSeasonRacesUseCase: GetCurrentSeasonRacesUseCase = mockk(relaxed = true)
    private val getUpcomingRacesUseCase: GetUpcomingRacesInCurrentSeasonUseCase = mockk(relaxed = true)
    private val getPastRacesUseCase: GetPastRacesInCurrentSeasonUseCase = mockk(relaxed = true)

    private lateinit var viewModel: RacesViewModel

    @Test
    fun `init should send success state when races are returned`() = runTest {
        val racesFlow = MutableSharedFlow<List<Race>>()
        val upcomingRacesFlow = MutableSharedFlow<List<Race>>()
        val pastRacesFlow = MutableSharedFlow<List<Race>>()
        every { getCurrentSeasonRacesUseCase() } returns racesFlow
        every { getUpcomingRacesUseCase() } returns upcomingRacesFlow
        every { getPastRacesUseCase() } returns pastRacesFlow

        setupViewModel()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.state.collect()
        }

        assertEquals(RacesState(), viewModel.state.value)

        racesFlow.emit(races)
        upcomingRacesFlow.emit(races)
        pastRacesFlow.emit(races)
        assertEquals(RacesState(races, races, races), viewModel.state.value)

        backgroundScope.cancel()
    }

    private fun setupViewModel() {
        viewModel = RacesViewModel(
            getCurrentSeasonRacesUseCase,
            getUpcomingRacesUseCase,
            getPastRacesUseCase
        )
    }
}
