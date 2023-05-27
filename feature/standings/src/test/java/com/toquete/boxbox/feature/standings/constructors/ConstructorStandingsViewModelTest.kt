package com.toquete.boxbox.feature.standings.constructors

import com.toquete.boxbox.core.model.ConstructorStanding
import com.toquete.boxbox.core.testing.data.constructorStandings
import com.toquete.boxbox.core.testing.util.MainDispatcherRule
import com.toquete.boxbox.data.constructorstandings.repository.ConstructorStandingsRepository
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

class ConstructorStandingsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repository: ConstructorStandingsRepository = mockk(relaxed = true)

    private lateinit var viewModel: ConstructorStandingsViewModel

    @Test
    fun `init should send success state when constructors standings are returned`() = runTest {
        val constructorsFlow = MutableSharedFlow<List<ConstructorStanding>>()
        coEvery { repository.getConstructorStandings() } returns constructorsFlow

        setupViewModel()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.state.collect()
        }

        assertEquals(ConstructorStandingsState.Loading, viewModel.state.value)

        constructorsFlow.emit(constructorStandings)
        assertEquals(ConstructorStandingsState.Success(constructorStandings), viewModel.state.value)

        backgroundScope.cancel()
    }

    private fun setupViewModel() {
        viewModel = ConstructorStandingsViewModel(repository)
    }
}
