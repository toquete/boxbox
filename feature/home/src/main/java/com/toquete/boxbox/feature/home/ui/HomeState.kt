package com.toquete.boxbox.feature.home.ui

internal data class HomeState(
    val isOffline: Boolean = false,
    val isSyncing: Boolean = false,
    val hasFailed: Boolean = false,
    val isRefreshing: Boolean = false,
    val isAdBannerVisible: Boolean = false
)
