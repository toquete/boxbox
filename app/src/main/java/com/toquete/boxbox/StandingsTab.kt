package com.toquete.boxbox

import androidx.annotation.StringRes

enum class StandingsTab(@StringRes val titleId: Int) {
    DRIVERS(R.string.drivers),
    CONSTRUCTORS(R.string.constructors)
}