package com.toquete.boxbox.feature.standings.constructors

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.toquete.boxbox.core.testing.data.fullConstructorStandings
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import org.junit.Rule
import org.junit.Test

class FullConstructorStandingItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun validateConstructorStandingItem() {
        with(composeTestRule) {
            setContent {
                BoxBoxTheme {
                    FullConstructorStandingItem(fullConstructorStandings.first())
                }
            }

            onNodeWithTag("Position").assertTextEquals("1")
            onNodeWithTag("Constructor Name").assertTextEquals("Red Bull")
            onNodeWithTag("Divider").assertIsDisplayed()
            onNodeWithTag("Points").assertTextEquals("100 PTS")
            onNodeWithTag("Wins").assertTextEquals("5 WINS")
        }
    }
}
