package com.toquete.boxbox.core.model

import kotlinx.datetime.Instant

data class Race(
    val season: String,
    val round: Int,
    val url: String,
    val name: String,
    val circuit: Circuit,
    val dateTime: Instant?,
    val firstPracticeDateTime: Instant?,
    val secondPracticeDateTime: Instant?,
    val thirdPracticeDateTime: Instant?,
    val qualifyingDateTime: Instant?,
    val sprintDateTime: Instant?
)
