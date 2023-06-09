package com.toquete.boxbox.core.network.interceptor

import com.google.firebase.crashlytics.FirebaseCrashlytics
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class NetworkErrorInterceptorTest {

    private val crashlytics: FirebaseCrashlytics = mockk(relaxed = true)
    private val interceptor = NetworkErrorInterceptor(crashlytics)

    @Test
    fun `intercept should send error to crashlytics when response is not successful`() {
        val request = Request.Builder()
            .url("http://test.com")
            .build()
        val response = Response.Builder()
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .message("client config invalid")
            .body("client config invalid".toResponseBody(null))
            .code(400)
            .build()
        val chain = mockk<Interceptor.Chain> {
            every { request() } returns request
            every { proceed(request) } returns response
        }
        val slot = slot<NetworkException>()
        val expected = "Network error with \"${response.message}\" message and ${response.code} status code. \n" +
            "Request: ${response.request} \n " +
            "Response: ${response.body}"

        interceptor.intercept(chain)

        verify { crashlytics.recordException(capture(slot)) }
        assertIs<NetworkException>(slot.captured)
        assertEquals(expected, slot.captured.message)
    }
}
