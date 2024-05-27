package com.toquete.boxbox.feature.raceresults.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.toquete.boxbox.core.testing.data.raceResults
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import org.junit.Rule
import org.junit.Test

class RaceResultScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testRaceResultScreen() {
        with(composeTestRule) {
            setContent {
                BoxBoxTheme {
                    RaceResultScreen(
                        state = RaceResultsState(
                            raceName = "Bahrain",
                            results = raceResults
                        )
                    )
                }
            }

            onNodeWithText("Bahrain").assertIsDisplayed()
            onNodeWithTag("Back Button").assertIsDisplayed()
            onNodeWithText("Driver").assertIsDisplayed()
            onNodeWithText("Max Verstappen").assertIsDisplayed()
        }
    }

    @Test
    fun testEmptyRaceResultScreen() {
        with(composeTestRule) {
            setContent {
                BoxBoxTheme {
                    RaceResultScreen(
                        state = RaceResultsState(
                            raceName = "Bahrain",
                            results = emptyList()
                        )
                    )
                }
            }

            onNodeWithText("No results available").assertIsDisplayed()
        }
    }
}
