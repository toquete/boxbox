package com.toquete.boxbox.feature.raceresults.ui

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollTo
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.toquete.boxbox.core.testing.data.raceResults
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RaceResultItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testRaceResultItem() {
        with(composeTestRule) {
            setContent {
                BoxBoxTheme {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .horizontalScroll(rememberScrollState())
                    ) {
                        RaceResultItem(
                            raceResult = raceResults.first()
                        )
                    }
                }
            }

            onNodeWithText("1").assertIsDisplayed()
            onNodeWithText("Max Verstappen").assertIsDisplayed()
            onNodeWithText("Red Bull").assertIsDisplayed()
            onNodeWithText("57", useUnmergedTree = true)
                .performScrollTo()
                .assertIsDisplayed()
            onNodeWithText("1:33:56.736", useUnmergedTree = true)
                .performScrollTo()
                .assertIsDisplayed()
            onNodeWithText("Finished", useUnmergedTree = true)
                .performScrollTo()
                .assertIsDisplayed()
            onNodeWithText("25", useUnmergedTree = true)
                .performScrollTo()
                .assertIsDisplayed()
        }
    }
}
