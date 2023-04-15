package com.toquete.boxbox.network.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
internal annotation class NetworkInterceptorOkHttpClient