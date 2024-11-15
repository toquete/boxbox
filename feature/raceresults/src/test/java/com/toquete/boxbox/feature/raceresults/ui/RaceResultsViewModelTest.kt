package com.toquete.boxbox.feature.raceresults.ui

import androidx.lifecycle.SavedStateHandle
import com.toquete.boxbox.core.model.RaceResult
import com.toquete.boxbox.core.testing.data.raceResults
import com.toquete.boxbox.core.testing.data.sprintRaceResults
import com.toquete.boxbox.core.testing.util.MainDispatcherRule
import com.toquete.boxbox.domain.raceresults.usecase.GetCurrentSeasonRaceResultsUseCase
import com.toquete.boxbox.domain.sprintresults.usecase.GetCurrentSeasonSprintResultsUseCase
import com.toquete.boxbox.feature.raceresults.navigation.RACE_ARGUMENT
import com.toquete.boxbox.feature.raceresults.navigation.ROUND_ARGUMENT
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

class RaceResultsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getCurrentSeasonRaceResultsUseCase: GetCurrentSeasonRaceResultsUseCase = mockk()
    private val getCurrentSeasonSprintResultsUseCase: GetCurrentSeasonSprintResultsUseCase = mockk()
    private val savedStateHandle = SavedStateHandle(
        mapOf(
            RACE_ARGUMENT to "Bahrain",
            ROUND_ARGUMENT to 1
        )
    )

    private lateinit var viewModel: RaceResultsViewModel

    @Test
    fun `init should send success state`() = runTest {
        val raceResultsFlow = MutableSharedFlow<List<RaceResult>>()
        val sprintResultsFlow = MutableSharedFlow<List<RaceResult>>()
        every { getCurrentSeasonRaceResultsUseCase(round = 1) } returns raceResultsFlow
        every { getCurrentSeasonSprintResultsUseCase(round = 1) } returns sprintResultsFlow

        setupViewModel()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.state.collect()
        }

        assertEquals(RaceResultsState(), viewModel.state.value)

        raceResultsFlow.emit(raceResults)
        sprintResultsFlow.emit(sprintRaceResults)
        assertEquals(RaceResultsState(raceName = "Bahrain", raceResults, sprintRaceResults), viewModel.state.value)

        backgroundScope.cancel()
    }

    private fun setupViewModel() {
        viewModel = RaceResultsViewModel(
            savedStateHandle,
            getCurrentSeasonRaceResultsUseCase,
            getCurrentSeasonSprintResultsUseCase
        )
    }
}
