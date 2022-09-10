package com.toquete.boxbox.standings.driver.presentation.model

import androidx.annotation.DrawableRes
import com.toquete.boxbox.R

enum class Nationality(
    val demonym: String,
    @DrawableRes val flagRes: Int
) {
    DUTCH(
        demonym = "Dutch",
        flagRes = R.drawable.flag_netherlands
    ),
    THAI(
        demonym = "Thai",
        flagRes = R.drawable.flag_thailand
    ),
    SPANISH(
        demonym = "Spanish",
        flagRes = R.drawable.flag_spain
    ),
    FINNISH(
        demonym = "Finnish",
        flagRes = R.drawable.flag_finland
    ),
    FRENCH(
        demonym = "French",
        flagRes = R.drawable.flag_france
    ),
    BRITISH(
        demonym = "British",
        flagRes = R.drawable.flag_great_britain
    ),
    GERMAN(
        demonym = "German",
        flagRes = R.drawable.flag_germany
    ),
    CANADIAN(
        demonym = "Canadian",
        flagRes = R.drawable.flag_canada
    ),
    MONEGASQUE(
        demonym = "Monegasque",
        flagRes = R.drawable.flag_monaco
    ),
    DANISH(
        demonym = "Danish",
        flagRes = R.drawable.flag_denmark
    ),
    MEXICAN(
        demonym = "Mexican",
        flagRes = R.drawable.flag_mexico
    ),
    AUSTRALIAN(
        demonym = "Australian",
        flagRes = R.drawable.flag_australia
    ),
    JAPANESE(
        demonym = "Japanese",
        flagRes = R.drawable.flag_japan
    ),
    CHINESE(
        demonym = "Chinese",
        flagRes = R.drawable.flag_china
    ),
    WORLD(
        demonym = "World",
        flagRes = R.drawable.flag_world_map
    );

    companion object {
        fun findByDemonym(demonym: String): Nationality {
            return values().find { it.demonym == demonym } ?: WORLD
        }
    }
}