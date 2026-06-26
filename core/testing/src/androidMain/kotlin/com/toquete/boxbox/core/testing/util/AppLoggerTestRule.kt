package com.toquete.boxbox.core.testing.util

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class AppLoggerTestRule : TestWatcher() {

    override fun starting(description: Description) {
        Napier.base(DebugAntilog())
    }

    override fun finished(description: Description) {
        Napier.takeLogarithm()
    }
}
