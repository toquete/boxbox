package com.toquete.boxbox.standings.drivers.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.toquete.boxbox.standings.drivers.domain.model.DriverStanding

class DriversStandingViewModel : ViewModel() {

    var state by mutableStateOf(DriversStandingState())
        private set

    init {
        state = state.copy(
            standings = listOf(
                DriverStanding(
                    position = 1,
                    driver = "Max Verstappen",
                    nationality = "NED",
                    car = "Red Bull",
                    points = 258
                )
            )
        )
    }
}