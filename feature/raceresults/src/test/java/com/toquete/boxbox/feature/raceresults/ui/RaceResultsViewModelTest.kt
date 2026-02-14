package com.toquete.boxbox.feature.raceresults.ui

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.testing.invoke
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.toquete.boxbox.core.model.RaceResult
import com.toquete.boxbox.core.navigation.BoxBoxRoute
import com.toquete.boxbox.core.testing.data.raceResults
import com.toquete.boxbox.core.testing.data.sprintRaceResults
import com.toquete.boxbox.core.testing.util.MainDispatcherRule
import com.toquete.boxbox.domain.usecase.GetCurrentSeasonRaceResultsUseCase
import com.toquete.boxbox.domain.usecase.GetCurrentSeasonSprintResultsUseCase
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
import org.junit.runner.RunWith
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class RaceResultsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getCurrentSeasonRaceResultsUseCase: GetCurrentSeasonRaceResultsUseCase = mockk()
    private val getCurrentSeasonSprintResultsUseCase: GetCurrentSeasonSprintResultsUseCase = mockk()
    private val savedStateHandle = SavedStateHandle(route = BoxBoxRoute.RaceResult(round = 1, race = "Bahrain"))

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
