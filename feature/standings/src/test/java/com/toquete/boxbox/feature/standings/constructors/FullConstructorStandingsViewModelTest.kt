package com.toquete.boxbox.feature.standings.constructors

import com.toquete.boxbox.core.model.FullConstructorStanding
import com.toquete.boxbox.core.testing.data.fullConstructorStandings
import com.toquete.boxbox.core.testing.util.MainDispatcherRule
import com.toquete.boxbox.domain.fullconstructorstandings.GetFullConstructorStandingsUseCase
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

class FullConstructorStandingsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getFullConstructorStandingsUseCase: GetFullConstructorStandingsUseCase = mockk(relaxed = true)

    private lateinit var viewModel: FullConstructorStandingsViewModel

    @Test
    fun `init should send success state when constructors standings are returned`() = runTest {
        val constructorsFlow = MutableSharedFlow<List<FullConstructorStanding>>()
        coEvery { getFullConstructorStandingsUseCase() } returns constructorsFlow

        setupViewModel()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.state.collect()
        }

        assertEquals(FullConstructorStandingsState.Loading, viewModel.state.value)

        constructorsFlow.emit(fullConstructorStandings)
        assertEquals(FullConstructorStandingsState.Success(fullConstructorStandings), viewModel.state.value)

        backgroundScope.cancel()
    }

    private fun setupViewModel() {
        viewModel = FullConstructorStandingsViewModel(getFullConstructorStandingsUseCase)
    }
}
