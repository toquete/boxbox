package com.toquete.boxbox.ui

sealed interface HomeIntent {
    data object Refresh : HomeIntent
}
