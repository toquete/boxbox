package com.toquete.boxbox.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.SportsScore
import androidx.compose.material.icons.outlined.EmojiEvents
import androidx.compose.ui.graphics.vector.ImageVector
import com.toquete.boxbox.feature.races.R as racesR
import com.toquete.boxbox.feature.standings.R as standingsR

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val iconTextId: Int
) {
    STANDINGS(
        selectedIcon = Icons.Filled.EmojiEvents,
        unselectedIcon = Icons.Outlined.EmojiEvents,
        iconTextId = standingsR.string.standings
    ),
    RACES(
        selectedIcon = Icons.Filled.SportsScore,
        unselectedIcon = Icons.Filled.SportsScore,
        iconTextId = racesR.string.races
    )
}
