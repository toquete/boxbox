package com.toquete.boxbox.core.network.interceptor

import okhttp3.Response

class NetworkException(private val response: Response) : Exception() {

    override val message: String
        get() = "Network error with \"${response.message}\" message and ${response.code} status code. \n" +
            "Request: ${response.request} \n " +
            "Response: ${response.body}"
}
