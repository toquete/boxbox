package com.toquete.boxbox.core.model

import kotlinx.datetime.LocalDateTime

data class Race(
    val season: String,
    val round: Int,
    val url: String,
    val name: String,
    val circuit: Circuit,
    val dateTime: LocalDateTime,
    val firstPracticeDateTime: LocalDateTime,
    val secondPracticeDateTime: LocalDateTime,
    val thirdPracticeDateTime: LocalDateTime?,
    val qualifyingDateTime: LocalDateTime,
    val sprintDateTime: LocalDateTime?
)
