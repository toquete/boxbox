package com.toquete.boxbox.feature.raceresults.ui

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollTo
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import org.junit.Rule
import org.junit.Test

class RaceResultHeaderTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun validateRaceResultHeader() {
        with(composeTestRule) {
            setContent {
                BoxBoxTheme {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .horizontalScroll(rememberScrollState())
                    ) {
                        RaceResultHeader()
                    }
                }
            }

            onNodeWithText("Pos").assertIsDisplayed()
            onNodeWithText("Driver").assertIsDisplayed()
            onNodeWithText("Constructor").assertIsDisplayed()
            onNodeWithText("Laps").assertIsDisplayed()
            onNodeWithText("Time", useUnmergedTree = true)
                .performScrollTo()
                .assertIsDisplayed()
            onNodeWithText("Status", useUnmergedTree = true)
                .performScrollTo()
                .assertIsDisplayed()
            onNodeWithText("Pts", useUnmergedTree = true)
                .performScrollTo()
                .assertIsDisplayed()
        }
    }
}
