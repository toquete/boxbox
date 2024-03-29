package com.toquete.boxbox.feature.standings.model

import androidx.annotation.StringRes
import com.toquete.boxbox.core.common.annotation.Generated
import com.toquete.boxbox.feature.standings.R

@Generated
enum class StandingsTab(@StringRes val titleId: Int) {
    DRIVERS(R.string.drivers),
    CONSTRUCTORS(R.string.constructors)
}
