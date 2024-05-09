package com.toquete.boxbox.navigation

import androidx.annotation.StringRes
import com.toquete.boxbox.R
import com.toquete.boxbox.feature.raceresults.navigation.RACE_RESULT_ROUTE
import com.toquete.boxbox.feature.races.navigation.RACES_ROUTE
import com.toquete.boxbox.feature.standings.navigation.STANDINGS_ROUTE
import com.toquete.boxbox.feature.races.R as racesR

enum class BoxBoxScreen(
    val route: String,
    @StringRes val titleTextId: Int? = null
) {
    STANDINGS(route = STANDINGS_ROUTE, titleTextId = R.string.app_name),
    RACES(route = RACES_ROUTE, titleTextId = racesR.string.races),
    RACE_RESULTS(route = RACE_RESULT_ROUTE)
}
