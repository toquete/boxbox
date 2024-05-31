package com.toquete.boxbox.feature.standings.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.toquete.boxbox.core.common.annotation.Generated
import com.toquete.boxbox.feature.standings.R
import com.toquete.boxbox.core.ui.R as uiR

@Generated
enum class StandingsTab(
    @StringRes val titleId: Int,
    @DrawableRes val iconId: Int
) {
    DRIVERS(R.string.standings_drivers, uiR.drawable.ic_person),
    CONSTRUCTORS(R.string.standings_constructors, uiR.drawable.ic_construction)
}
