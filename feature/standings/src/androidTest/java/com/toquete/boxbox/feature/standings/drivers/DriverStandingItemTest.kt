package com.toquete.boxbox.feature.standings.drivers

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.toquete.boxbox.core.testing.data.driverStandings
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import org.junit.Rule
import org.junit.Test

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
            onNodeWithTag("Points").assertTextEquals("90 PTS")
            onNodeWithTag("Wins").assertTextEquals("5 WINS")
            onNodeWithTag("Constructor").assertTextEquals("Red Bull")
            onNodeWithTag("Driver", useUnmergedTree = true).assertIsDisplayed()
            onNodeWithTag("Flag").assertIsDisplayed()
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
    fun checkDriverimageIsDisplayedOnNumberClick() {
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
