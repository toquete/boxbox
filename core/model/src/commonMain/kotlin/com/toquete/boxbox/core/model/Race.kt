package com.toquete.boxbox.core.model

import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
data class Race(
    val season: String,
    val round: Int,
    val url: String?,
    val name: String,
    val circuit: Circuit,
    val dateTime: Instant?,
    val firstPracticeDateTime: Instant?,
    val secondPracticeDateTime: Instant?,
    val thirdPracticeDateTime: Instant?,
    val qualifyingDateTime: Instant?,
    val sprintDateTime: Instant?
)
