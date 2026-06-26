package com.toquete.boxbox.core.common.log

import io.github.aakira.napier.Napier

object AppLogger {
    fun d(message: String, tag: String? = null) = Napier.d(message = message, tag = tag)
    fun w(message: String, tag: String? = null) = Napier.w(message = message, tag = tag)
    fun e(throwable: Throwable? = null, message: String = "", tag: String? = null) =
        Napier.e(message = message, throwable = throwable, tag = tag)
}
