package com.toquete.boxbox.fake

import com.toquete.boxbox.core.common.util.NetworkMonitor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeNetworkMonitor : NetworkMonitor {
    override val isOnline: Flow<Boolean>
        get() = flowOf(true)
}
