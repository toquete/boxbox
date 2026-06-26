package com.toquete.boxbox.log

import com.google.firebase.crashlytics.FirebaseCrashlytics
import io.github.aakira.napier.Antilog
import io.github.aakira.napier.LogLevel

class CrashlyticsAntilog(
    private val crashlytics: FirebaseCrashlytics
) : Antilog() {

    override fun performLog(
        priority: LogLevel,
        tag: String?,
        throwable: Throwable?,
        message: String?
    ) {
        if (priority == LogLevel.ERROR && throwable != null) {
            crashlytics.recordException(throwable)
        } else {
            crashlytics.log(message ?: "")
        }
    }
}
