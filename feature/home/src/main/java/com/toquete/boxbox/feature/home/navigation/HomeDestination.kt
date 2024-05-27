package com.toquete.boxbox.feature.home.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.SportsScore
import androidx.compose.material.icons.outlined.EmojiEvents
import androidx.compose.ui.graphics.vector.ImageVector
import com.toquete.boxbox.feature.home.R

internal enum class HomeDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val iconTextId: Int,
    @StringRes val titleTextId: Int
) {
    STANDINGS(
        selectedIcon = Icons.Filled.EmojiEvents,
        unselectedIcon = Icons.Outlined.EmojiEvents,
        iconTextId = R.string.standings,
        titleTextId = R.string.app_name
    ),
    RACES(
        selectedIcon = Icons.Filled.SportsScore,
        unselectedIcon = Icons.Filled.SportsScore,
        iconTextId = R.string.races,
        titleTextId = R.string.races
    )
}
