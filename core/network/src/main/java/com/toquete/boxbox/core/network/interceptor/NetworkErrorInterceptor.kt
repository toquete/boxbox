package com.toquete.boxbox.core.network.interceptor

import com.toquete.boxbox.core.common.log.AppLogger
import okhttp3.Interceptor
import okhttp3.Response

internal class NetworkErrorInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        if (!response.isSuccessful) {
            sendError(response)
        }

        return response
    }

    private fun sendError(response: Response) {
        AppLogger.e(NetworkException(response))
    }
}
