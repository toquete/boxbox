package com.toquete.boxbox.core.network.interceptor

import okhttp3.logging.HttpLoggingInterceptor.Logger
import timber.log.Timber

private const val TAG = "OkHttp"

internal class HttpLogger : Logger {

    override fun log(message: String) {
        Timber.tag(TAG).d(message)
    }
}
