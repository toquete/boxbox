package com.toquete.boxbox.core.testing.util

import timber.log.Timber

class TestTree : Timber.Tree() {
    val logs = mutableListOf<Log>()

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        logs.add(Log(priority, tag, message, t))
    }
}
