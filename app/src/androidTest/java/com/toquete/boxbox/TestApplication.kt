package com.toquete.boxbox

import android.app.Application
import com.toquete.boxbox.di.prodModule
import com.toquete.boxbox.di.testModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin

class TestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TestApplication)
            modules(prodModule, testModule)
            workManagerFactory()
        }
    }
}
