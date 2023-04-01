package com.toquete.boxox.data.driverstandings.source.remote.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

internal class NetworkConnectionInterceptor @Inject constructor(
    @ApplicationContext private val context: Context
) : ConnectivityManager.NetworkCallback(), Interceptor {

    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private var isOnline = false

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(this)
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            isOnline = connectivityManager.activeNetworkInfo?.isConnected ?: false
        }

        if (isOnline) {
            return chain.proceed(chain.request())
        } else {
            throw NoConnectionException()
        }
    }

    override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
        super.onCapabilitiesChanged(network, networkCapabilities)
        isOnline = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

}