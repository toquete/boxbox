package com.toquete.boxbox.navigation

import com.toquete.boxbox.feature.races.navigation.RACES_ROUTE
import com.toquete.boxbox.feature.standings.navigation.STANDINGS_ROUTE

enum class TopLevelDestination(val route: String) {
    STANDINGS(route = STANDINGS_ROUTE),
    RACES(route = RACES_ROUTE)
}
