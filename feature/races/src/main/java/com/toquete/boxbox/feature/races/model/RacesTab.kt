package com.toquete.boxbox.feature.races.model

import androidx.annotation.StringRes
import com.toquete.boxbox.feature.races.R

internal enum class RacesTab(@StringRes val titleId: Int) {
    UPCOMING(R.string.races_upcoming),
    PAST(R.string.races_past)
}
