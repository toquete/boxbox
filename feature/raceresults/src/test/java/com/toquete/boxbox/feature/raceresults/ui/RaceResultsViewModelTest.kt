package com.toquete.boxbox.feature.raceresults.ui

import androidx.lifecycle.SavedStateHandle
import com.toquete.boxbox.core.model.RaceResult
import com.toquete.boxbox.core.testing.data.raceResults
import com.toquete.boxbox.core.testing.util.MainDispatcherRule
import com.toquete.boxbox.domain.raceresults.usecase.GetCurrentSeasonRaceResultsUseCase
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
    private val savedStateHandle = SavedStateHandle(mapOf("round" to 1))

    private lateinit var viewModel: RaceResultsViewModel

    @Test
    fun `init should send success state`() = runTest {
        val raceResultsFlow = MutableSharedFlow<List<RaceResult>>()
        every { getCurrentSeasonRaceResultsUseCase(round = 1) } returns raceResultsFlow

        setupViewModel()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.state.collect()
        }

        assertEquals(RaceResultsState(), viewModel.state.value)

        raceResultsFlow.emit(raceResults)
        assertEquals(RaceResultsState(raceResults), viewModel.state.value)

        backgroundScope.cancel()
    }

    private fun setupViewModel() {
        viewModel = RaceResultsViewModel(savedStateHandle, getCurrentSeasonRaceResultsUseCase)
    }
}
