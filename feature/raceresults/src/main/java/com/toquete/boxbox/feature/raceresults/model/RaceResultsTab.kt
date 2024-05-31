package com.toquete.boxbox.feature.raceresults.model

import androidx.annotation.StringRes
import com.toquete.boxbox.feature.raceresults.R

internal enum class RaceResultsTab(@StringRes val titleId: Int) {
    RACE(R.string.race_results_race),
    SPRINT(R.string.race_results_sprint)
}
