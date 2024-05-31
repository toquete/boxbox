package com.toquete.boxbox.feature.standings.constructors

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.toquete.boxbox.core.testing.data.constructorStandings
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import org.junit.Rule
import org.junit.Test

class ConstructorStandingItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun validateConstructorStandingItem() {
        with(composeTestRule) {
            setContent {
                BoxBoxTheme {
                    ConstructorStandingItem(constructorStandings.first())
                }
            }

            onNodeWithTag("Position").assertTextEquals("1")
            onNodeWithTag("Constructor Name").assertTextEquals("Red Bull")
            onNodeWithTag("Divider").assertIsDisplayed()
            onNodeWithTag("Points", useUnmergedTree = true).assertTextEquals("123 PTS")
            onNodeWithTag("Wins", useUnmergedTree = true).assertTextEquals("3 WINS")
            onNodeWithTag("Constructor Image").assertIsDisplayed()
            onNodeWithTag("Flag", useUnmergedTree = true).assertIsDisplayed()
        }
    }
}
