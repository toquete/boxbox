package com.toquete.boxbox.feature.standings.drivers

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.toquete.boxbox.core.testing.data.driverStandings
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DriverStandingItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun validateDriverStandingItem() {
        with(composeTestRule) {
            setContent {
                BoxBoxTheme {
                    DriverStandingItem(driverStandings.first())
                }
            }

            onNodeWithTag("Position").assertTextEquals("1")
            onNodeWithTag("First Name").assertTextEquals("Max")
            onNodeWithTag("Last Name").assertTextEquals("Verstappen")
            onNodeWithTag("Divider").assertIsDisplayed()
            onNodeWithTag("Points", useUnmergedTree = true).assertTextEquals("90 PTS")
            onNodeWithTag("Wins", useUnmergedTree = true).assertTextEquals("5 WINS")
            onNodeWithTag("Constructor", useUnmergedTree = true).assertIsDisplayed()
            onNodeWithTag("Driver", useUnmergedTree = true).assertIsDisplayed()
            onNodeWithTag("Flag", useUnmergedTree = true).assertIsDisplayed()
        }
    }

    @Test
    fun checkDriverNumberIsDisplayedOnImageClick() {
        with(composeTestRule) {
            setContent {
                BoxBoxTheme {
                    DriverStandingItem(driverStandings.first())
                }
            }

            onNodeWithTag("Driver", useUnmergedTree = true).performClick()
            onNodeWithTag("Number", useUnmergedTree = true).assertIsDisplayed()
        }
    }

    @Test
    fun checkDriverImageIsDisplayedOnNumberClick() {
        with(composeTestRule) {
            setContent {
                BoxBoxTheme {
                    DriverStandingItem(driverStandings.first())
                }
            }

            onNodeWithTag("Driver", useUnmergedTree = true).performClick()
            onNodeWithTag("Number", useUnmergedTree = true).performClick()
            onNodeWithTag("Driver", useUnmergedTree = true).assertIsDisplayed()
        }
    }
}
