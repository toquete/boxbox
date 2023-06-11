package com.toquete.boxbox.core.testing.util

import org.junit.rules.TestWatcher
import org.junit.runner.Description
import timber.log.Timber

class TestTimberRule(
    val testTree: TestTree = TestTree()
) : TestWatcher() {

    override fun starting(description: Description) {
        Timber.plant(testTree)
    }

    override fun finished(description: Description) {
        Timber.uprootAll()
    }
}
