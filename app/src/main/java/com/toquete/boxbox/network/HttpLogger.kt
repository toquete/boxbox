package com.toquete.boxbox.network

import com.toquete.boxbox.core.common.log.AppLogger
import okhttp3.logging.HttpLoggingInterceptor.Logger

private const val TAG = "OkHttp"

internal class HttpLogger : Logger {

    override fun log(message: String) {
        AppLogger.d(message, tag = TAG)
    }
}
