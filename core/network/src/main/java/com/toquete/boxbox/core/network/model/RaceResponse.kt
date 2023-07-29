package com.toquete.boxbox.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RaceResponse(
    @SerialName("season")
    val season: String,
    @SerialName("round")
    val round: String,
    @SerialName("url")
    val url: String,
    @SerialName("raceName")
    val raceName: String,
    @SerialName("Circuit")
    val circuit: CircuitResponse,
    @SerialName("date")
    val date: String,
    @SerialName("time")
    val time: String,
    @SerialName("FirstPractice")
    val firstPractice: PracticeResponse,
    @SerialName("SecondPractice")
    val secondPractice: PracticeResponse,
    @SerialName("ThirdPractice")
    val thirdPractice: PracticeResponse?,
    @SerialName("Qualifying")
    val qualifying: PracticeResponse,
    @SerialName("Sprint")
    val sprint: PracticeResponse?
)
