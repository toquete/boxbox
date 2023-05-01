package com.toquete.boxbox.feature.standings.drivers

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onNodeWithTag
import com.toquete.boxbox.core.testing.data.fullDriverStandings
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import org.junit.Rule
import org.junit.Test

class FullDriverStandingItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun validateDriverStandingItem() {
        with(composeTestRule) {
            setContent {
                BoxBoxTheme {
                    FullDriverStandingItem(fullDriverStandings.first())
                }
            }

            onNodeWithTag("Position").assertTextEquals("1")
            onNodeWithTag("First Name").assertTextEquals("Max")
            onNodeWithTag("Last Name").assertTextEquals("Verstappen")
            onNodeWithTag("Divider").assertIsDisplayed()
            onNodeWithTag("Points")
                .onChild()
                .assertTextEquals("90 PTS")
            onNodeWithTag("Wins")
                .onChild()
                .assertTextEquals("5 WINS")
            onNodeWithTag("Constructor")
                .onChild()
                .assertTextEquals("Red Bull")
        }
    }
}