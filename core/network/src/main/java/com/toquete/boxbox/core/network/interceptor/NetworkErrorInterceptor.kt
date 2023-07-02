package com.toquete.boxbox.core.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

internal class NetworkErrorInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        if (!response.isSuccessful) {
            sendError(response)
        }

        return response
    }

    private fun sendError(response: Response) {
        Timber.e(NetworkException(response))
    }
}
