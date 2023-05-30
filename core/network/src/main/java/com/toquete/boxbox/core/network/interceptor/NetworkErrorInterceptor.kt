package com.toquete.boxbox.core.network.interceptor

import com.google.firebase.crashlytics.FirebaseCrashlytics
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

internal class NetworkErrorInterceptor @Inject constructor(
    private val crashlytics: FirebaseCrashlytics
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        if (!response.isSuccessful) {
            sendError(response)
        }

        return response
    }

    private fun sendError(response: Response) {
        crashlytics.recordException(NetworkException(response))
    }
}
