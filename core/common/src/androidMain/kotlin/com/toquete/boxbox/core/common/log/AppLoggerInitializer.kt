package com.toquete.boxbox.core.common.log

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

object AppLoggerInitializer {
    fun init() {
        Napier.base(DebugAntilog())
    }
}
