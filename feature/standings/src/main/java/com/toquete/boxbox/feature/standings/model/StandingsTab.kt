package com.toquete.boxbox.feature.standings.model

import androidx.annotation.StringRes
import com.toquete.boxbox.feature.standings.R

enum class StandingsTab(@StringRes val titleId: Int) {
    DRIVERS(R.string.drivers),
    CONSTRUCTORS(R.string.constructors)
}
