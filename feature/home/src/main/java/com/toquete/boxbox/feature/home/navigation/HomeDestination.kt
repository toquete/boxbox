package com.toquete.boxbox.feature.home.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.SportsScore
import androidx.compose.material.icons.outlined.EmojiEvents
import androidx.compose.ui.graphics.vector.ImageVector
import com.toquete.boxbox.core.navigation.RacesRoute
import com.toquete.boxbox.core.navigation.StandingsRoute
import com.toquete.boxbox.feature.home.R
import kotlin.reflect.KClass

internal enum class HomeDestination(
    val route: Any,
    val routeClass: KClass<*>,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val iconTextId: Int,
    @StringRes val titleTextId: Int
) {
    STANDINGS(
        route = StandingsRoute,
        routeClass = StandingsRoute::class,
        selectedIcon = Icons.Filled.EmojiEvents,
        unselectedIcon = Icons.Outlined.EmojiEvents,
        iconTextId = R.string.home_standings,
        titleTextId = R.string.home_app_name
    ),
    RACES(
        route = RacesRoute,
        routeClass = RacesRoute::class,
        selectedIcon = Icons.Filled.SportsScore,
        unselectedIcon = Icons.Filled.SportsScore,
        iconTextId = R.string.home_races,
        titleTextId = R.string.home_races
    )
}
